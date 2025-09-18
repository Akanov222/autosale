package com.example.autosale.controller;

import com.example.autosale.dao.*;
import com.example.autosale.dto.*;
import com.example.autosale.repository.MinivanRepository;
import com.example.autosale.repository.SedanRepository;
import com.example.autosale.repository.TruckRepository;
import com.example.autosale.service.CarTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/cars")
@Tag(name = "Car API", description = "Управление автомобилями в автосалоне")
public class CarController {

    private final SedanRepository sedanRepository;
    private final TruckRepository truckRepository;
    private final MinivanRepository minivanRepository;
    private final CarTypeService carTypeService;
    private final CarFactory carFactory;
    private final com.fasterxml.jackson.databind.ObjectMapper objectMapper;

    @Autowired
    public CarController(SedanRepository sedanRepository,
                         TruckRepository truckRepository,
                         MinivanRepository minivanRepository,
                         CarTypeService carTypeService,
                         CarFactory carFactory,
                         com.fasterxml.jackson.databind.ObjectMapper objectMapper) {
        this.sedanRepository = sedanRepository;
        this.truckRepository = truckRepository;
        this.minivanRepository = minivanRepository;
        this.carTypeService = carTypeService;
        this.carFactory = carFactory;
        this.objectMapper = objectMapper;
    }

    @Operation(summary = "Получить все автомобили", description = "Возвращает автомобили всех типов")
    @ApiResponse(responseCode = "200", description = "Успешное получение списка")
    @ApiResponse(responseCode = "400", description = "Ошибка валидации")
    @GetMapping
    public List<CarResponse> getAllCars() {
        return Stream.of(
                        sedanRepository.findAll().stream(),
                        truckRepository.findAll().stream(),
                        minivanRepository.findAll().stream()
                )
                .flatMap(s -> s)
                .map(CarResponse::fromCar)
                .toList();
    }

    @Operation(summary = "Список автомобилей по типу", description = "Возвращает все записи данного подтипа")
    @ApiResponse(responseCode = "200", description = "Успешное получение списка")
    @ApiResponse(responseCode = "400", description = "Ошибка валидации")
    @GetMapping("/{type}")
    public List<CarResponse> getCarsByType(@PathVariable String type) {
        String t = type.toUpperCase();
        return switch (t) {
            case "SEDAN" -> sedanRepository.findAll().stream().map(CarResponse::fromCar).toList();
            case "TRUCK" -> truckRepository.findAll().stream().map(CarResponse::fromCar).toList();
            case "MINIVAN" -> minivanRepository.findAll().stream().map(CarResponse::fromCar).toList();
            default -> List.of();
        };
    }

    @Operation(summary = "Получить автомобиль по ID", description = "Возвращает запись данного подтипа по идентификатору")
    @ApiResponse(responseCode = "200", description = "Автомобиль найден")
    @ApiResponse(responseCode = "404", description = "Автомобиль не найден")
    @GetMapping("/{type}/{id}")
    public ResponseEntity<CarResponse> getOneCarById(@PathVariable String type, @PathVariable Long id) {
        return switch (type.toUpperCase()) {
            case "SEDAN" -> sedanRepository.findById(id).map(CarResponse::fromCar)
                    .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
            case "TRUCK" -> truckRepository.findById(id).map(CarResponse::fromCar)
                    .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
            case "MINIVAN" -> minivanRepository.findById(id).map(CarResponse::fromCar)
                    .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
    }

    @Operation(summary = "Создать автомобиль подтипа", description = "Создаёт запись. Общие поля обязательны, тип-специфичные поля валидируются контроллером подтипа")
    @ApiResponse(responseCode = "200", description = "Создано")
    @ApiResponse(responseCode = "400", description = "Ошибка валидации")
    @PostMapping("/{type}")
    @RequestBody(required = true, content = @Content(schema = @Schema(oneOf = {SedanRequest.class, TruckRequest.class, MinivanRequest.class})))
    public ResponseEntity<String> createCar(@PathVariable String type, @org.springframework.web.bind.annotation.RequestBody java.util.Map<String, Object> payload) {
        try {
            CarRequest request = convertByType(type, payload);
            Car car = carFactory.createCar(type, request);
            car.setType(carTypeService.getCarTypeByName(type.toUpperCase()));

            return saveCarByType(type, car);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    private void validateRequestType(String type, CarRequest request) {
        String upperType = type.toUpperCase();

        if (upperType.equals("SEDAN")) {
            throw new IllegalArgumentException("For SEDAN type, use SedanRequest DTO");
        }
        if (upperType.equals("TRUCK")) {
            throw new IllegalArgumentException("For TRUCK type, use TruckRequest DTO");
        }
        if (upperType.equals("MINIVAN")) {
            throw new IllegalArgumentException("For MINIVAN type, use MinivanRequest DTO");
        }
    }

    private ResponseEntity<String> saveCarByType(String type, Car car) {
        switch (type.toUpperCase()) {
            case "SEDAN" -> {
                Sedan sedan = (Sedan) car;
                sedanRepository.save(sedan);
                return ResponseEntity.ok("Sedan created with ID: " + sedan.getId());
            }
            case "TRUCK" -> {
                Truck truck = (Truck) car;
                truckRepository.save(truck);
                return ResponseEntity.ok("Truck created with ID: " + truck.getId());
            }
            case "MINIVAN" -> {
                Minivan minivan = (Minivan) car;
                minivanRepository.save(minivan);
                return ResponseEntity.ok("Minivan created with ID: " + minivan.getId());
            }
            default -> throw new IllegalArgumentException("Unknown car type: " + type);
        }
    }

    @io.swagger.v3.oas.annotations.Operation(summary = "Обновить автомобиль подтипа", description = "Обновляет запись. Тип-специфичные поля валидируются контроллером подтипа")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Обновлено")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Ошибка валидации")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Не найдено")
    @PutMapping("/{type}/{id}")
    @RequestBody(required = true, content = @Content(schema = @Schema(oneOf = {SedanRequest.class, TruckRequest.class, MinivanRequest.class})))
    public ResponseEntity<String> update(@PathVariable String type, @PathVariable Long id, @org.springframework.web.bind.annotation.RequestBody java.util.Map<String, Object> payload) {
       try {
           CarRequest request = convertByType(type, payload);
           Car car = carFactory.createCar(type, request);
           car.setType(carTypeService.getCarTypeByName(type.toUpperCase()));
           return updateCarByTypeAndId(type, car, id);
       } catch (IllegalArgumentException e) {
           return ResponseEntity.badRequest().body(e.getMessage());
       } catch (Exception e) {
           return ResponseEntity.badRequest().body("Error: " + e.getMessage());
       }
    }

    private CarRequest convertByType(String type, java.util.Map<String, Object> payload) {
        String t = type.toUpperCase();
        return switch (t) {
            case "SEDAN" -> objectMapper.convertValue(payload, SedanRequest.class);
            case "TRUCK" -> objectMapper.convertValue(payload, TruckRequest.class);
            case "MINIVAN" -> objectMapper.convertValue(payload, MinivanRequest.class);
            default -> throw new IllegalArgumentException("Unknown car type: " + type);
        };
    }

    private ResponseEntity<String> updateCarByTypeAndId(String type, Car updatedCar, Long id) {
        return switch (type.toUpperCase()) {
            case "SEDAN" -> sedanRepository.findById(id).map(existing -> {
                Sedan existingSedan = existing;
                Sedan source = (Sedan) updatedCar;
                existingSedan.setBrand(source.getBrand());
                existingSedan.setModel(source.getModel());
                existingSedan.setYear(source.getYear());
                existingSedan.setType(source.getType());
                existingSedan.setPrice(source.getPrice());
                existingSedan.setTrunkCapacity(source.getTrunkCapacity());
                sedanRepository.save(existingSedan);
                return ResponseEntity.ok("Sedan updated with ID: " + existingSedan.getId());
            }).orElse(ResponseEntity.notFound().build());

            case "TRUCK" -> truckRepository.findById(id).map(existing -> {
                Truck existingTruck = existing;
                Truck source = (Truck) updatedCar;
                existingTruck.setBrand(source.getBrand());
                existingTruck.setModel(source.getModel());
                existingTruck.setYear(source.getYear());
                existingTruck.setType(source.getType());
                existingTruck.setPrice(source.getPrice());
                existingTruck.setLoadCapacity(source.getLoadCapacity());
                truckRepository.save(existingTruck);
                return ResponseEntity.ok("Truck updated with ID: " + existingTruck.getId());
            }).orElse(ResponseEntity.notFound().build());

            case "MINIVAN" -> minivanRepository.findById(id).map(existing -> {
                Minivan existingMinivan = existing;
                Minivan source = (Minivan) updatedCar;
                existingMinivan.setBrand(source.getBrand());
                existingMinivan.setModel(source.getModel());
                existingMinivan.setYear(source.getYear());
                existingMinivan.setType(source.getType());
                existingMinivan.setPrice(source.getPrice());
                existingMinivan.setSeatingCapacity(source.getSeatingCapacity());
                minivanRepository.save(existingMinivan);
                return ResponseEntity.ok("Minivan updated with ID: " + existingMinivan.getId());
            }).orElse(ResponseEntity.notFound().build());

            default -> throw new IllegalArgumentException("Unknown car type: " + type);
        };
    }


    @Operation(summary = "Удалить автомобиль по ID", description = "Удаляет запись данного подтипа по идентификатору")
    @ApiResponse(responseCode = "200", description = "Автомобиль успешно удален")
    @ApiResponse(responseCode = "404", description = "Автомобиль не найден")
    @DeleteMapping("/{type}/{id}")
    public ResponseEntity<String> deleteCar(@PathVariable String type, @PathVariable Long id) {
        String t = type.toUpperCase();
        switch (t) {
            case "SEDAN" -> {
                if (sedanRepository.existsById(id)) {
                    sedanRepository.deleteById(id);
                    return ResponseEntity.ok("Car ID:" + id + " is deleted!");
                }
            }
            case "TRUCK" -> {
                if (truckRepository.existsById(id)) {
                    truckRepository.deleteById(id);
                    return ResponseEntity.ok("Car ID:" + id + " is deleted!");
                }
            }
            case "MINIVAN" -> {
                if (minivanRepository.existsById(id)) {
                    minivanRepository.deleteById(id);
                    return ResponseEntity.ok("Car ID:" + id + " is deleted!");
                }
            }
            default -> {
                throw new IllegalStateException("Unexpected value: " + t);
            }
        };
        return ResponseEntity.notFound().build();
    }
}

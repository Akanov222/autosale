package com.example.autosale.controller;

import com.example.autosale.dao.*;
import com.example.autosale.dto.*;
import com.example.autosale.repository.MinivanRepository;
import com.example.autosale.repository.SedanRepository;
import com.example.autosale.repository.TruckRepository;
import com.example.autosale.service.CarService;
import com.example.autosale.service.CarTypeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
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
    private final CarService carService;
    private final ObjectMapper objectMapper;

    @Autowired
    public CarController(SedanRepository sedanRepository,
                         TruckRepository truckRepository,
                         MinivanRepository minivanRepository,
                         CarTypeService carTypeService,
                         CarFactory carFactory, CarService carService,
                         ObjectMapper objectMapper) {
        this.sedanRepository = sedanRepository;
        this.truckRepository = truckRepository;
        this.minivanRepository = minivanRepository;
        this.carTypeService = carTypeService;
        this.carFactory = carFactory;
        this.carService = carService;
        this.objectMapper = objectMapper;
    }

    @Operation(summary = "Получить все автомобили",
            description = "Возвращает автомобили всех типов")
    @ApiResponse(responseCode = "200", description = "Успешное получение списка")
    @ApiResponse(responseCode = "400", description = "Ошибка валидации")
    @GetMapping
    public List<CarResponse> getAllCars() {
        return Stream.of(
                        "SEDAN", "TRUCK", "MINIVAN"
                )
                .flatMap(type -> carService.getRepository(type).findAll().stream())
                .map(CarResponse::fromCar)
                .toList();
    }

    @Operation(summary = "Список автомобилей по типу",
            description = "Возвращает все записи данного подтипа")
    @ApiResponse(responseCode = "200", description = "Успешное получение списка")
    @ApiResponse(responseCode = "400", description = "Ошибка валидации")
    @GetMapping("/{type}")
    public List<CarResponse> getCarsByType(@PathVariable String type) {
        return carService.getRepository(type).findAll().stream()
                .map(CarResponse::fromCar)
                .toList();
    }

    @Operation(summary = "Получить автомобиль по ID",
            description = "Возвращает запись данного подтипа по идентификатору")
    @ApiResponse(responseCode = "200", description = "Автомобиль найден")
    @ApiResponse(responseCode = "404", description = "Автомобиль не найден")
    @GetMapping("/{type}/{id}")
    public ResponseEntity<CarResponse> getOneCarById(@PathVariable String type,
                                                     @PathVariable Long id) {
        return carService.getRepository(type).findById(id)
                .map(CarResponse::fromCar)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Создать автомобиль подтипа",
            description = "Создаёт запись. Общие поля обязательны, " +
                    "тип-специфичные поля валидируются контроллером подтипа")
    @ApiResponse(responseCode = "200", description = "Создано")
    @ApiResponse(responseCode = "400", description = "Ошибка валидации")
    @PostMapping("/{type}")
    public ResponseEntity<String> createSedan(@PathVariable String type,
                                              @RequestBody Map<String, Object> payload) {
        try {
            CarRequest request = carService.convertRequest(type, payload);
            Car car = carFactory.createCar(type, request);
            return carService.saveCar(type, car);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
/*

    @Operation(summary = "Обновить автомобиль подтипа",
            description = "Обновляет запись. Тип-специфичные поля валидируются контроллером подтипа")
    @ApiResponse(responseCode = "200", description = "Обновлено")
    @ApiResponse(responseCode = "400", description = "Ошибка валидации")
    @ApiResponse(responseCode = "404", description = "Не найдено")
    @PutMapping("/{type}/{id}")
//    @RequestBody(required = true, content = @Content(schema = @Schema(oneOf = {SedanRequest.class, TruckRequest.class, MinivanRequest.class})))
    public ResponseEntity<String> update(@PathVariable String type, @PathVariable Long id,
                                         @RequestBody Map<String, Object> payload) {
        try {
            CarRequest request = convertByType(type, payload);
            Car car = carFactory.createCar(type, request);
            car.setCarTypeId(carTypeService.getCarTypeByName(type.toUpperCase()).getId());
            return updateCarByTypeAndId(type, car, id);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
*/
/*
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
    }*/
}

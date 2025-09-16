package com.example.autosale.controller;

import com.example.autosale.dao.*;
import com.example.autosale.dto.CarRequest;
import com.example.autosale.dto.CarResponse;
import com.example.autosale.repository.MinivanRepository;
import com.example.autosale.repository.SedanRepository;
import com.example.autosale.repository.TruckRepository;
import com.example.autosale.service.CarTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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

    public CarController(SedanRepository sedanRepository,
                         TruckRepository truckRepository,
                         MinivanRepository minivanRepository,
                         CarTypeService carTypeService) {
        this.sedanRepository = sedanRepository;
        this.truckRepository = truckRepository;
        this.minivanRepository = minivanRepository;
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
        String t = type.toUpperCase();
        return switch (t) {
            case "SEDAN" -> sedanRepository.findById(id).map(CarResponse::fromCar)
                    .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
            case "TRUCK" -> truckRepository.findById(id).map(CarResponse::fromCar)
                    .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
            case "MINIVAN" -> minivanRepository.findById(id).map(CarResponse::fromCar)
                    .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
            default -> throw new IllegalStateException("Unexpected value: " + t);
        };
    }

    @Operation(summary = "Создать автомобиль подтипа", description = "Создаёт запись. Общие поля обязательны, тип-специфичные поля валидируются контроллером подтипа")
    @ApiResponse(responseCode = "200", description = "Создано")
    @ApiResponse(responseCode = "400", description = "Ошибка валидации")
    @PostMapping("/{type}")
    public ResponseEntity<String> create(@PathVariable String type, @Valid @RequestBody CarRequest req) {
        String t = type.toUpperCase();
        Car car;
        switch (t) {
            case "SEDAN" -> car = new Sedan();
            case "TRUCK" -> car = new Truck();
            case "MINIVAN" -> car = new Minivan();
            default -> {
                throw new IllegalStateException("Unexpected value: " + t);
                return ResponseEntity.notFound().build();
            }
        };
        car.setBrand(req.brand());
        car.setModel(req.model());
        car.setYear(req.year());
        car.setType(req.type());
        car.setPrice(req.price());

        switch (t) {
            case "SEDAN" -> sedanRepository.save(car);
            case "TRUCK" -> truckRepository.save(car);
            case "MINIVAN" -> minivanRepository.save(car);
            default -> throw new IllegalStateException("Unexpected value: " + t);
        };
        return ResponseEntity.ok("Auto Created");
    }

   /* @io.swagger.v3.oas.annotations.Operation(summary = "Обновить автомобиль подтипа", description = "Обновляет запись. Тип-специфичные поля валидируются контроллером подтипа")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Обновлено")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Ошибка валидации")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Не найдено")
    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @Valid @RequestBody CarRequest req, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getFieldError() != null ? result.getFieldError().getDefaultMessage() : "Validation error");
        }
        try {
            validateRequiredOnUpdate(req);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
        return repository.findById(id).map(car -> {
            CarType carType = carTypeService.getCarTypeByName(fixedTypeName);
            car.setBrand(req.brand());
            car.setModel(req.model());
            car.setYear(req.year());
            car.setType(carType);
            car.setPrice(req.price());
            applySubtypeFieldsOnUpdate(car, req);
            repository.save(car);
            return ResponseEntity.ok(fixedTypeName + " updated");
        }).orElse(ResponseEntity.notFound().build());
    }*/


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

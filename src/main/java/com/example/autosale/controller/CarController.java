package com.example.autosale.controller;

import com.example.autosale.dao.*;
import com.example.autosale.dto.CarRequest;
import com.example.autosale.dto.CarResponse;
import com.example.autosale.repository.CarRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cars")
@Tag(name = "Car API", description = "Управление автомобилями в автосалоне")
public class CarController {

private final CarRepository carRepository;

    public CarController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Operation(summary = "Получить все автомобили")
    @ApiResponse(responseCode = "200", description = "Успешное получение списка")
    @GetMapping
    public List<CarResponse> getAllCars() {
        return carRepository.findAll().stream()
                .map(CarResponse::fromCar)
                .toList();
    }

    @Operation(summary = "Получить автомобили по типу")
    @ApiResponse(responseCode = "200", description = "Список автомобилей по типу")
    @GetMapping("/type/{type}")
    public List<CarResponse> getCarsByType(@PathVariable CarType type) {
        return carRepository.findByType(type).stream()
                .map(CarResponse::fromCar)
                .toList();
    }

    @Operation(summary = "Получить автомобиль по ID")
    @ApiResponse(responseCode = "200", description = "Автомобиль найден")
    @ApiResponse(responseCode = "404", description = "Автомобиль не найден")
    @GetMapping("/{id}")
    public ResponseEntity<CarResponse> getOneCarById(@PathVariable Long id) {
        return carRepository.findById(id)
                .map(CarResponse::fromCar)  // Используем наш метод преобразования
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Добавить новый автомобиль")
    @ApiResponse(responseCode = "200", description = "Автомобиль успешно добавлен")
    @ApiResponse(responseCode = "400", description = "Некорректные данные автомобиля")
    @PostMapping
    public ResponseEntity<String> addCar(@Valid @RequestBody CarRequest request) {
        Car car = switch(request.type()) {
            case SEDAN -> {
                Sedan sedan = new Sedan();
                sedan.setTrunkCapacity(request.trunkCapacity());
                yield sedan;
            }
            case TRUCK -> {
                Truck truck = new Truck();
                truck.setLoadCapacity(request.loadCapacity());
                yield truck;
            }
            case MINIVAN -> {
                Minivan minivan = new Minivan();
                minivan.setSeatingCapacity(request.seatingCapacity());
                yield minivan;
            }
        };

        car.setBrand(request.brand());
        car.setModel(request.model());
        car.setYear(request.year());
        car.setType(request.type());
        car.setPrice(request.price());

        carRepository.save(car);
        return ResponseEntity.ok("Car is included!");
    }

    @Operation(summary = "Обновить информацию об автомобиле")
    @ApiResponse(responseCode = "200", description = "Автомобиль успешно обновлен")
    @ApiResponse(responseCode = "400", description = "Некорректные данные")
    @ApiResponse(responseCode = "404", description = "Автомобиль не найден")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCar(@PathVariable Long id,
                                       @Valid @RequestBody CarRequest request,
                                       BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest()
                    .body(result.getFieldError().getDefaultMessage());
        }
        return carRepository.findById(id)
                .map(car -> {
                    car.setBrand(request.brand());
                    car.setModel(request.model());
                    car.setYear(request.year());
                    car.setType(request.type());
                    car.setPrice(request.price());

                    if (car instanceof Sedan && request.trunkCapacity() != null) {
                        ((Sedan) car).setTrunkCapacity(request.trunkCapacity());
                    } else if (car instanceof Truck && request.loadCapacity() != null) {
                        ((Truck) car).setLoadCapacity(request.loadCapacity());
                    } else if (car instanceof Minivan && request.seatingCapacity() != null) {
                        ((Minivan) car).setSeatingCapacity(request.seatingCapacity());
                    }

                    carRepository.save(car);
                    return ResponseEntity.ok("Автомобиль обновлён!");
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Удалить автомобиль")
    @ApiResponse(responseCode = "200", description = "Автомобиль успешно удален")
    @ApiResponse(responseCode = "404", description = "Автомобиль не найден")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCar(@PathVariable Long id) {
        carRepository.deleteById(id);
        return ResponseEntity.ok("Car ID:" + id + " is deleted!");
    }
}

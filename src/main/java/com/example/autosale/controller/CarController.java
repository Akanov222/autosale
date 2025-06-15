package com.example.autosale.controller;

import com.example.autosale.dao.Car;
import com.example.autosale.dto.CarRequest;
import com.example.autosale.dto.CarResponse;
import com.example.autosale.repository.CarRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    private final CarRepository carRepository;

    public CarController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @GetMapping
    public List<CarResponse> getAllCars() {
        return carRepository.findAll().stream()
                .map(car -> new CarResponse(
                        car.getId(),
                        car.getBrand(),
                        car.getModel(),
                        car.getPrice()
                ))
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarResponse> getOneCarById(@PathVariable Long id) {
        return carRepository.findById(id)
                .map(car -> ResponseEntity.ok(new CarResponse(
                        car.getId(),
                        car.getBrand(),
                        car.getModel(),
                        car.getPrice()
                )))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<String> addCar(@Valid @RequestBody CarRequest request) {
        Car car = new Car();
        car.setBrand(request.brand());
        car.setModel(request.model());
        car.setPrice(request.price());
        carRepository.save(car);
        return ResponseEntity.ok("Car is included!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCar(@PathVariable Long id) {
        carRepository.deleteById(id);
        return ResponseEntity.ok("Car ID:" + id + " is deleted!");
    }
}

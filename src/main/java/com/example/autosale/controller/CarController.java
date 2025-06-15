package com.example.autosale.controller;

import com.example.autosale.dao.Car;
import com.example.autosale.repository.CarRepository;
import jakarta.validation.Valid;
import org.apache.logging.log4j.Logger;
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
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Car> getOneCarById(@PathVariable Long id) {
        return carRepository.findById(id);
    }

    @PostMapping
    public ResponseEntity<String> addCar(@Valid @RequestBody Car newCar) {
        carRepository.save(newCar);
        return ResponseEntity.ok("Car is included!");
    }

    @DeleteMapping("/{id}")
    public String deleteCar(@PathVariable Long id) {
        carRepository.deleteById(id);
        return "Car ID:" + id + " is deleted!";
    }

}

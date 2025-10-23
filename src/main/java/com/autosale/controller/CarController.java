package com.autosale.controller;

import com.autosale.dto.*;
import com.autosale.model.entity.car.*;
import com.autosale.service.factory.CarFactory;
import com.autosale.service.port.input.CarService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.autosale.model.entity.car.CarTypeEnum;

@RestController
@RequestMapping("/api/cars")
@Tag(name = "Car API", description = "Управление автомобилями в автосалоне")
public class CarController {

    private final Map<String, CarFactory> factories;
    private final Map<String, CarService> services;

    public CarController(List<CarService> services, List<CarFactory> factories) {
        this.factories = factories.stream().collect(Collectors.toMap(CarFactory::getType, f -> f));
        this.services = services.stream().collect(Collectors.toMap(CarService::getType, s -> s));
    }

    @PostMapping("/{type}")
        public ResponseEntity<?> createCar(@PathVariable String type,
            @RequestBody CarRequestDTO requestDTO) {

        CarFactory factory = factories.get(type.toUpperCase());
        CarService service = services.get(type.toUpperCase());

        try {
            Car specificCar = factory.createCar(type, requestDTO);
            service.saveCar(specificCar);
            return ResponseEntity.ok(CarResponse.fromCar(specificCar));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }

    }
}



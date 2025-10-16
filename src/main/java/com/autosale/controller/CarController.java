package com.autosale.controller;

import com.autosale.dto.*;
import com.autosale.model.entity.car.Car;
import com.autosale.model.entity.car.Minivan;
import com.autosale.model.entity.car.Sedan;
import com.autosale.model.entity.car.Truck;
import com.autosale.service.factory.CarFactory;
import com.autosale.service.port.input.CarService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.autosale.model.entity.car.CarTypeEnum.*;

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

        CarRequestDTO specificRequestDTO = null;
        Car specificCar = null;
        CarFactory factory = factories.get(type.toUpperCase());
        CarService service = services.get(type.toUpperCase());
        System.out.println("point_1");
        if ("SEDAN".equalsIgnoreCase(type)) {
            specificRequestDTO = (SedanRequestDTO) requestDTO;
            specificCar = new Sedan();
        } else if ("MINIVAN".equalsIgnoreCase(type)) {
            specificRequestDTO = (MinivanRequestDTO) requestDTO;
            specificCar = new Minivan();
        } else if ("TRUCK".equalsIgnoreCase(type)) {
            specificRequestDTO = (TruckRequestDTO) requestDTO;
            specificCar = new Truck();
        }
        System.out.println("point_2");
        try {
            System.out.println("point_3");
            specificCar = factory.createCar(type, specificRequestDTO);
            System.out.println("point_4");
            service.saveCar(specificCar);
            System.out.println("point_5");
            return ResponseEntity.ok(CarResponse.fromCar(specificCar));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}

package com.autosale.controller;

import com.autosale.dto.*;
import com.autosale.model.entity.car.Car;
import com.autosale.service.factory.CarFactory;
import com.autosale.service.factory.CarRequestFactory;
import com.autosale.service.port.input.CarService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cars")
@Tag(name = "Car API", description = "Управление автомобилями в автосалоне")
public class CarController {

    private final Map<String, CarFactory> factories;
    private final Map<String, CarService> services;
    private final ObjectMapper objectMapper;

    //    @Operation(summary = "Создать автомобиль", description = "Создаёт автомобиль указанного типа")
//    @ApiResponse(responseCode = "200", description = "Автомобиль создан")
//    @ApiResponse(responseCode = "400", description = "Ошибка валидации")
    @PostMapping("/create/sedan")
    public ResponseEntity<?> createCar(@RequestBody SedanRequestDTO request) {
        String carType = "SEDAN";

//        SedanRequestDTO request = objectMapper.convertValue(payload, SedanRequestDTO.class);
//        switch (carType.toUpperCase()) {
//            case "SEDAN":
//                request = objectMapper.convertValue(payload, SedanRequestDTO.class);
//                break;
//            case "MINIVAN":
//                request = objectMapper.convertValue(payload, MinivanRequestDTO.class);
//                break;
//            case "TRUCK":
//                request = objectMapper.convertValue(payload, TruckRequestDTO.class);
//                break;
//            default:
//                return ResponseEntity.badRequest().body("Unsupported car type");
//        }

        try {
            CarFactory factory = factories.get(carType.toUpperCase());
            CarService service = services.get(carType.toUpperCase());

            Car car = factory.createCar(carType, request);
            service.saveCar(car);

            return ResponseEntity.ok(CarResponse.fromCar(car));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    public CarController(List<CarService> services, List<CarFactory> factories,
                         ObjectMapper objectMapper) {
        this.factories = factories.stream().collect(Collectors.toMap(CarFactory::getType, f -> f));
        this.services = services.stream().collect(Collectors.toMap(CarService::getType, s -> s));
        this.objectMapper = objectMapper;
    }
}

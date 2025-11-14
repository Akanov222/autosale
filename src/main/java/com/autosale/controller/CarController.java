package com.autosale.controller;

import com.autosale.dto.*;
import com.autosale.exception.InvalidCarTypeException;
import com.autosale.service.port.input.CarService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/cars")
@Tag(name = "Car API", description = "Car dealership management")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @GetMapping("/{type}")
    public ResponseEntity<Page<CarResponseDto>> getAllCars(
            @PathVariable String type,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String direction) {

        Page<CarResponseDto> cars = carService.getAllCars(type, page, size, sortBy, direction);
        return ResponseEntity.ok(cars);
//        Пример запроса: GET /api/cars/sedan?page=0&size=5&sortBy=price&direction=DESC
    }

    @GetMapping("/{type}/{id}")
    public ResponseEntity<CarResponseDto> getCarById(@PathVariable String type, @PathVariable Long id) {
        return carService.getCarById(type, id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/{type}")
    public ResponseEntity<?> createCar(
            @PathVariable String type, @RequestBody CarDto requestDTO) {
        return carService.saveCar(type, requestDTO)
                .map(carResponseDto ->
                        ResponseEntity.created(URI.create("/api/cars/" + type + "/" + carResponseDto.getId()))
                                .body(carResponseDto)
                )
                .orElseThrow(() -> new InvalidCarTypeException("Invalid car type: " + type));
    }

    @DeleteMapping("/{type}/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable String type, @PathVariable Long id) {
        boolean deleted = carService.deleteCar(type, id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}



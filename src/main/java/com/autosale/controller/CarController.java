package com.autosale.controller;

import com.autosale.dto.*;
import com.autosale.exception.InvalidCarTypeException;
import com.autosale.model.entity.car.Car;
import com.autosale.service.port.input.CarService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/cars")
@Tag(name = "Car API", description = "Car dealership management")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @GetMapping("/{type}/{id}")
    public ResponseEntity<CarResponseDto> getCarById(@PathVariable String type, @PathVariable Long id) {
        return carService.getCarById(type, id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{type}/search")
    public ResponseEntity<Page<CarResponseDto>> searchCars(
            @PathVariable String type,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String model,
            @RequestParam(required = false) Integer minYear,
            @RequestParam(required = false)  Integer maxYear,
            @RequestParam(required = false)  BigDecimal minPrice,
            @RequestParam(required = false)  BigDecimal maxPrice,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        CarSearchCriteria criteria = CarSearchCriteria.builder()
                .brand(brand)
                .model(model)
                .minYear(minYear)
                .maxYear(maxYear)
                .minPrice(minPrice)
                .maxPrice(maxPrice)
                .build();

        Page<CarResponseDto> result = carService.searchCars(type, criteria, page, size);
        return ResponseEntity.ok(result);
//        Пример запроса: GET /api/cars/sedan/search?brand=Mercedes&model=S-Class&minYear=2020&maxYear=2023&minPrice=80000&maxPrice=100000&page=0&size=10
    }

/*    @GetMapping("/{type}")
    public ResponseEntity<Page<CarResponseDto>> getAllCars(
            @PathVariable String type,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String direction) {

        Page<CarResponseDto> cars = carService.getAllCars(type, page, size, sortBy, direction);
        return ResponseEntity.ok(cars);
//        Пример запроса: GET /api/cars/sedan?page=0&size=5&sortBy=price&direction=DESC
    }   */

    @GetMapping("/{type}")
    public ResponseEntity<Page<CarDto>> getAllCars(
            @PathVariable String type,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String direction) {

        Page<CarDto> cars = carService.getAllCars(type, page, size, sortBy, direction);
        return ResponseEntity.ok(cars);
//        Пример запроса: GET /api/cars/sedan?page=0&size=5&sortBy=price&direction=DESC
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

    @PutMapping("/{type}/{id}")
    public ResponseEntity<?> updateCar(
            @PathVariable String type, @PathVariable Long id, @RequestBody CarDto requestDTO) {
        Optional<CarResponseDto> updateCar = carService.updateCar(type, id, requestDTO);
        return updateCar.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/{type}/{id}")
    public ResponseEntity<?> partialUpdateCar(
            @PathVariable String type, @PathVariable Long id, @RequestBody CarUpdateDto updateDto) {
        Optional<CarResponseDto> updatedCar = carService.partialUpdateCar(type, id, updateDto);
        return updatedCar.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{type}/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable String type, @PathVariable Long id) {
        boolean deleted = carService.deleteCar(type, id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}



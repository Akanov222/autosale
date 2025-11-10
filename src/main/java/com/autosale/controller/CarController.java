package com.autosale.controller;

import com.autosale.dto.*;
import com.autosale.service.port.input.CarService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;


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

    @PostMapping("/{type}")
    public ResponseEntity<?> createCar(
            @PathVariable String type, @RequestBody CarDto requestDTO) {
        return carService.saveCar(type, requestDTO)
                .map(carResponseDto ->
                        ResponseEntity.created(URI.create("/api/cars/" + type + "/" + carResponseDto.getId()))
                                .body(carResponseDto)
                )
                                .orElseGet(() -> ResponseEntity.notFound().build());
    }

/*
        CarResponseDto carResponseDto = carService.saveCar(type, requestDTO);
.body("Invalid car type")
        if (carResponseDto == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("A car with this chassis number is already exists");
        }

        return ResponseEntity.created(URI.create("/api/cars/type/" + carResponseDto.getId()))
                .body(carResponseDto);*/

    @DeleteMapping("/{type}/{id}")
    public void deleteCar(@PathVariable String type, @PathVariable Long id) {
        carService.deleteCar(type, id);
    }
}



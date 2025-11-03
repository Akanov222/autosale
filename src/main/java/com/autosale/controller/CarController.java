package com.autosale.controller;

import com.autosale.dto.*;
import com.autosale.service.port.input.CarService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/cars")
@Tag(name = "Car API", description = "Управление автомобилями в автосалоне")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/{type}/{id}")
    public CarDto getCarById(@PathVariable String type, @PathVariable Long id) {
        return carService.getCarById(type, id);
    }

    @PostMapping("/{type}")
    public void /*ResponseEntity<?>*/ createCar(
            @PathVariable String type, @RequestBody CarDto requestDTO) {
        carService.saveCar(type, requestDTO);
    }

    @DeleteMapping("/{type}/{id}")
    public void deleteCar(@PathVariable String type, @PathVariable Long id) {
        carService.deleteCar(type, id);
    }
}



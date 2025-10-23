package com.autosale.controller;

import com.autosale.dto.*;
import com.autosale.service.port.input.CarService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/cars")
@Tag(name = "Car API", description = "Управление автомобилями в автосалоне")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping("/{type}")
        public void /*ResponseEntity<?>*/ createCar(@PathVariable String type, @RequestBody CarRequestDTO requestDTO) {

        carService.saveCar(type, requestDTO);

    }
}



package com.autosale.controller;

import com.autosale.dto.CarRequestDTO;
import com.autosale.dto.MinivanRequestDTO;
import com.autosale.dto.SedanRequestDTO;
import com.autosale.dto.TruckRequestDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cars")
@Tag(name = "Car API", description = "Управление автомобилями в автосалоне")
public class CarController {

    @PostMapping("/{type}")
    public void createCar(@PathVariable String type, @RequestBody CarRequestDTO requestDTO) {
        System.out.println("Car type: " + type);
        System.out.println("Car type name: " + requestDTO.getCarTypeName());
        System.out.println("Brand: " + requestDTO.getBrand());
        System.out.println("Model: " + requestDTO.getModel());
        System.out.println("Year: " + requestDTO.getYear());
        System.out.println("Price: " + requestDTO.getPrice());
        System.out.println("Price: " + requestDTO.getPrice());

        // Специфичные поля для каждого типа
        if (requestDTO instanceof SedanRequestDTO) {
            SedanRequestDTO sedan = (SedanRequestDTO) requestDTO;
            System.out.println("Sedan specific feature: " + sedan.getTrunkCapacity());
        } else if (requestDTO instanceof MinivanRequestDTO) {
            MinivanRequestDTO minivan = (MinivanRequestDTO) requestDTO;
            System.out.println("Minivan specific feature: " + minivan.getSeatingCapacity());
        } else if (requestDTO instanceof TruckRequestDTO) {
            TruckRequestDTO truck = (TruckRequestDTO) requestDTO;
            System.out.println("Truck specific feature: " + truck.getLoadCapacity());
        }
    }
}

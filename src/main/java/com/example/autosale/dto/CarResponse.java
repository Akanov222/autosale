package com.example.autosale.dto;

import com.example.autosale.dao.*;

import java.math.BigDecimal;
import java.util.Optional;

public record CarResponse(
        Long id,
        String brand,
        String model,
        Integer year,
        CarType type,
        BigDecimal price,

        // Опциональные поля для специфичных характеристик
        Optional<Double> trunkCapacity,
        Optional<Double> loadCapacity,
        Optional<Double> seatingCapacity
) {
    public static CarResponse fromCar(Car car) {
        return new CarResponse(
                car.getId(),
                car.getBrand(),
                car.getModel(),
                car.getYear(),
                car.getType(),
                car.getPrice(),
                Optional.ofNullable(car instanceof Sedan ? ((Sedan) car).getTrunkCapacity() : null),
                Optional.ofNullable(car instanceof Truck ? ((Truck) car).getLoadCapacity() : null),
                Optional.ofNullable(car instanceof Minivan ? ((Minivan) car).getSeatingCapacity() : null)
        );
    }
}

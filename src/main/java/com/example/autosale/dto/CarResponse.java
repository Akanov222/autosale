package com.example.autosale.dto;

import com.example.autosale.model.*;

import java.math.BigDecimal;
import java.util.Optional;

public record CarResponse(
        String brand,
        String model,
        Integer year,
//        Long carTypeId,
        BigDecimal price,

        Optional<Double> trunkCapacity,
        Optional<Double> loadCapacity,
        Optional<Double> seatingCapacity
) {
    public static CarResponse fromCar(Car car) {
        return new CarResponse(
                car.getBrand(),
                car.getModel(),
                car.getYear(),
                car.getPrice(),
                Optional.ofNullable(car instanceof Sedan ? ((Sedan) car).getTrunkCapacity() : null),
                Optional.ofNullable(car instanceof Truck ? ((Truck) car).getLoadCapacity() : null),
                Optional.ofNullable(car instanceof Minivan ? ((Minivan) car).getSeatingCapacity() : null)
        );
    }
}

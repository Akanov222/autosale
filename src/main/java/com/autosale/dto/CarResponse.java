package com.autosale.dto;

import com.autosale.model.entity.car.*;

import java.math.BigDecimal;
import java.util.Optional;

public record CarResponse(
        String brand,
        String model,
        Integer year,
        CarType carType,
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
                car.getCarType(),
                car.getPrice(),
                Optional.ofNullable(car instanceof Sedan ? ((Sedan) car).getTrunkCapacity() : null),
                Optional.ofNullable(car instanceof Truck ? ((Truck) car).getLoadCapacity() : null),
                Optional.ofNullable(car instanceof Minivan ? ((Minivan) car).getSeatingCapacity() : null)
        );
    }
}

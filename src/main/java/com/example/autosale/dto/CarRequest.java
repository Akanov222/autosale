package com.example.autosale.dto;

import com.example.autosale.dao.CarType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

//POST, PUT
public record CarRequest(

        @NotBlank(message = "Brand can not be blank!")
        String brand,

        @NotBlank(message = "Model can not be blank!")
        String model,

        @NotNull(message = "Year can not be blank!")
        Integer year,

        @NotNull
        CarType type,

        @PositiveOrZero(message = "Price must be positive or zero!")
        BigDecimal price,

        // Опциональные поля для специфичных характеристик
        Double trunkCapacity,
        Double loadCapacity,
        Double seatingCapacity
) {
}

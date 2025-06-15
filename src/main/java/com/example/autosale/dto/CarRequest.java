package com.example.autosale.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

//POST, PUT
public record CarRequest(

        @NotBlank(message = "Brand can not be blank!")
        String brand,

        @NotBlank(message = "Model can not be blank!")
        String model,

        @PositiveOrZero(message = "Price must be positive or zero!")
        double price
) {
}

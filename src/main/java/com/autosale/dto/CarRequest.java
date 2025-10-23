package com.autosale.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

// POST, PUT
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarRequest {

        @NotBlank(message = "Brand can not be blank!")
        String brand;

        @NotBlank(message = "Model can not be blank!")
        String model;

        @NotNull(message = "Year can not be blank!")
        Integer year;

        @Nullable
        Long carTypeId;

        @PositiveOrZero(message = "Price must be positive or zero!")
        BigDecimal price;
}

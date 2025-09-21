package com.example.autosale.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class MinivanRequest extends CarRequest {

    @NotNull(message = "Seating capacity is required")
    @PositiveOrZero(message = "Seating capacity must be positive")
    Double seatingCapacity;

    public MinivanRequest(String brand, String model, Integer year,
                          @Nullable Long carTypeId, BigDecimal price, Double seatingCapacity) {
        super(brand, model, year, carTypeId, price);
        this.seatingCapacity = seatingCapacity;
    }
}
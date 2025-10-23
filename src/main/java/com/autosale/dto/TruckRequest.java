package com.autosale.dto;

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
public class TruckRequest extends CarRequest {
        @NotNull(message = "Load capacity is required")
        @PositiveOrZero(message = "Load capacity must be positive")
        Double loadCapacity;

        public TruckRequest(String brand, String model, Integer year,
                            @Nullable Long carTypeId, BigDecimal price, Double loadCapacity) {
                super(brand, model, year, carTypeId, price);
                this.loadCapacity = loadCapacity;
        }
}

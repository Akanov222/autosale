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
public class SedanRequest extends CarRequest {
       @NotNull(message = "Trunk capacity is required")
       @PositiveOrZero(message = "Trunk capacity must be positive")
       Double trunkCapacity;

       public SedanRequest(String brand, String model, Integer year,
                           @Nullable Long carTypeId, BigDecimal price, Double trunkCapacity) {
              super(brand, model, year, carTypeId, price);
              this.trunkCapacity = trunkCapacity;
       }
}

package com.autosale.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class SedanRequestDTO extends CarRequestDTO {

    private Double trunkCapacity;

    public SedanRequestDTO(String brand, String model, Integer year,
                             String carTypeName, BigDecimal price,
                             Double trunkCapacity) {
        super(brand, model, year, carTypeName, price);
        this.trunkCapacity = trunkCapacity;
    }
}

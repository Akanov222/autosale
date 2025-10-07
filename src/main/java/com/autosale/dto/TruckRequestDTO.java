package com.autosale.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class TruckRequestDTO extends CarRequestDTO{
    private Double loadCapacity;

    public TruckRequestDTO(String brand, String model, Integer year,
                        String carTypeName, BigDecimal price,
                        Double loadCapacity) {
        super(brand, model, year, carTypeName, price);
        this.loadCapacity = loadCapacity;
    }
}

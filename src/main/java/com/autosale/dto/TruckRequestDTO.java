package com.autosale.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class TruckRequestDTO extends CarRequestDTO {

    @JsonProperty("loadCapacity")
    private Double loadCapacity;


    public TruckRequestDTO(String brand, String model, Integer year,
                           String carTypeName, BigDecimal price,
                           Double loadCapacity) {
        super(brand, model, year, carTypeName, price);
        this.loadCapacity = loadCapacity;
    }

    @Override
    public String getCarTypeName() {
        return "TRUCK";
    }
}

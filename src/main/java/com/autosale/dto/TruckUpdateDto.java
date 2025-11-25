package com.autosale.dto;

import com.autosale.model.enums.CarTypeEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class TruckUpdateDto extends CarUpdateDto {

    @JsonProperty("loadCapacity")
    private Double loadCapacity;

    public TruckUpdateDto(String brand, String model, Integer year,
                    String carTypeName, BigDecimal price,
                    Double loadCapacity) {
        super(brand, model, year, price, carTypeName);
        this.loadCapacity = loadCapacity;
    }

    @Override
    public String getCarTypeName() {
        return CarTypeEnum.TRUCK.getCode();
    }
}
package com.autosale.dto;

import com.autosale.model.entity.car.CarTypeEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class SedanRequestDTO extends CarRequestDTO {

    @JsonProperty("trunkCapacity")
    private Double trunkCapacity;

    public SedanRequestDTO(String brand, String model, Integer year,
                           String carTypeName, BigDecimal price,
                           Double trunkCapacity) {
        super(brand, model, year, carTypeName, price);
        this.trunkCapacity = trunkCapacity;
        System.out.println("SEDAN is created");
    }

    @Override
    public String getCarTypeName() {
        return CarTypeEnum.SEDAN.getCode();
    }
}

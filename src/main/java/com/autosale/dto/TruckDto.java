package com.autosale.dto;

import com.autosale.model.enums.CarTypeEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TruckDto extends CarDto {

    @JsonProperty("loadCapacity")
    private Double loadCapacity;

    public TruckDto(Long id, String brand, String model, Integer year,
                    String carTypeName, BigDecimal price,
                    Double loadCapacity) {
        super(id, brand, model, year, carTypeName, price);
        this.loadCapacity = loadCapacity;
    }

    @Override
    public String getCarTypeName() {
        return CarTypeEnum.TRUCK.getCode();
    }

    @Override
    public String toString() {
        return "TruckRequestDTO{" +
                "loadCapacity=" + loadCapacity +
                '}';
    }
}

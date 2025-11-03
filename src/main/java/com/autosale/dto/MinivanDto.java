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
public class MinivanDto extends CarDto {

    @JsonProperty("seatingCapacity")
    private Double seatingCapacity;

    public MinivanDto(String brand, String model, Integer year,
                      String carTypeName, BigDecimal price,
                      Double seatingCapacity) {
        super(brand, model, year, carTypeName, price);
        this.seatingCapacity = seatingCapacity;
    }

    @Override
    public String getCarTypeName() {
        return CarTypeEnum.MINIVAN.getCode();
    }

    @Override
    public String toString() {
        return "MinivanRequestDTO{" +
                "seatingCapacity=" + seatingCapacity +
                '}';
    }
}

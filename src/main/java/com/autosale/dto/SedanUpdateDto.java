package com.autosale.dto;

import com.autosale.model.enums.CarTypeEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class SedanUpdateDto extends CarUpdateDto {

    @JsonProperty("trunkCapacity")
    @Min(value = 10, message = "Trunk capacity must be at least 10")
    @Max(value = 4000, message = "Trunk capacity must be at most 4000")
    private Double trunkCapacity;

    public SedanUpdateDto(String brand, String model, Integer year,
                    String carTypeName, BigDecimal price,
                    Double trunkCapacity) {
        super(brand, model, year, price, carTypeName);
        this.trunkCapacity = trunkCapacity;
        System.out.println("SEDAN is created");
    }

    @Override
    public String getCarTypeName() {
        return CarTypeEnum.SEDAN.getCode();
    }
}
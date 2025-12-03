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
public class MinivanDto extends CarDto {

    @JsonProperty("seatingCapacity")
    private Double seatingCapacity;

    public MinivanDto(Long id, String brand, String model, Integer year,
                      String carTypeName, BigDecimal price,
                      Double seatingCapacity) {
        super(id, brand, model, year, carTypeName, price);
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

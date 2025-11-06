package com.autosale.dto;

import com.autosale.model.enums.CarTypeEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class SedanResponseDto extends CarResponseDto {

    private Double trunkCapacity;

    public SedanResponseDto(Long id, String brand, String model, Integer year,
                            String carTypeName, BigDecimal price,
                            Double trunkCapacity) {
        super(id, brand, model, year, carTypeName, price);
        this.trunkCapacity = trunkCapacity;
    }

    @Override
    public String getCarTypeName() {
        return CarTypeEnum.SEDAN.getCode();
    }

    @Override
    public String toString() {
        return "SedanRequestDTO{" +
                "trunkCapacity=" + trunkCapacity +
                '}';
    }
}

package com.autosale.dto;

import com.autosale.model.entity.car.CarType;
import com.autosale.model.enums.CarTypeEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class TruckResponseDto extends CarResponseDto {

    private Double loadCapacity;

    public TruckResponseDto(Long id, String brand, String model, Integer year,
                            CarType carTypeName, BigDecimal price,
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

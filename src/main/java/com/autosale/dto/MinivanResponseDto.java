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
public class MinivanResponseDto extends CarResponseDto {

    private Double seatingCapacity;

    public MinivanResponseDto(Long id, String brand, String model, Integer year,
                              CarType carTypeName, BigDecimal price,
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

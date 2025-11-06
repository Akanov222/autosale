package com.autosale.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class CarResponseDto {

    @Getter
    private Long id;

    @Getter
    private String brand;

    @Getter
    private String model;

    @Getter
    private Integer year;

    private String carTypeName;

    @Getter
    private BigDecimal price;

    public abstract String getCarTypeName();
}

package com.autosale.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarRequestDTO {
    private String brand;
    private String model;
    private Integer year;
    private String carTypeName;
    private BigDecimal price;
}

package com.autosale.dto;

import com.autosale.model.entity.car.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class CarResponse {
    protected String brand;
    protected String model;
    protected Integer year;
    protected CarType carType;
    protected BigDecimal price;

    abstract String getCarTypeName(String type);
}

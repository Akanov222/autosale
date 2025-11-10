package com.autosale.dto;

import com.autosale.model.entity.car.CarType;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @ManyToOne(fetch = FetchType.LAZY)  // Указываем связь с CarType
    @JoinColumn(name = "car_type_id", referencedColumnName = "id", nullable = false)
    private CarType carTypeName;

    @Getter
    private BigDecimal price;

    public abstract String getCarTypeName();
}

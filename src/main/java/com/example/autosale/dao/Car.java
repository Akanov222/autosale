package com.example.autosale.dao;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public abstract class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Brand can not be blank!")
    private String brand;

    @NotBlank(message = "Model can not be blank!")
    private String model;

    @NotNull(message = "Year can not ba blank!")
    private Integer year;

    @Nullable
    @JoinColumn(name = "car_type_id")
    private Long carTypeId;

    @PositiveOrZero(message = "Price must be positive or zero!")
    private BigDecimal price;

    public Car(String brand, String model, Integer year, Long carTypeId,
               BigDecimal price) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.carTypeId = carTypeId;
        this.price = price;
    }
}

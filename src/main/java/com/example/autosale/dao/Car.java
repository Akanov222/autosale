package com.example.autosale.dao;

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

    @ManyToOne
    @JoinColumn(name = "car_type_id")
    private CarType type;

    @NotNull(message = "Year can not ba blank!")
    private Integer year;

    @PositiveOrZero(message = "Price must be positive or zero!")
    private BigDecimal price;

    public Car(String brand, String model, Integer year, CarType type, BigDecimal price) {
        this.brand = brand;
        this.model = model;
        this.type = type;
        this.year = year;
        this.price = price;
    }
}

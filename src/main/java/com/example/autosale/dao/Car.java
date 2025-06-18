package com.example.autosale.dao;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "car_type")
public abstract class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Brand can not be blank!")
    @Column(nullable = false)
    private String brand;

    @NotBlank(message = "Model can not be blank!")
    @Column(nullable = false)
    private String model;

    @NotNull(message = "Year can not ba blank!")
    @Column(nullable = false)
    private Integer year;

    @Enumerated(EnumType.STRING)
    private CarType type;

    @PositiveOrZero(message = "Price must be positive or zero!")
    @Column(nullable = false)
    private BigDecimal price;

    public Car() {
    }

    public Car(Long id, String brand, String model, Integer year,
               CarType type, BigDecimal price) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.type = type;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public CarType getType() {
        return type;
    }

    public void setType(CarType type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}

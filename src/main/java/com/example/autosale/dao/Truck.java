package com.example.autosale.dao;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@Table(name = "truck")
public class Truck extends Car{

    @Getter
    @Setter
    @Column(name = "load_capacity")
    private Double loadCapacity;

    public Truck(String brand, String model,
                 Integer year, CarType type, BigDecimal price, Double loadCapacity) {
        super(brand, model, year, type, price);
        this.loadCapacity = loadCapacity;
    }
}

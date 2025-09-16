package com.example.autosale.dao;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@Table(name = "minivan")
public class Minivan extends Car {

    @Getter
    @Setter
    @Column(name = "seating_capacity")
    private Double seatingCapacity;

    public Minivan(String brand, String model, Integer year,
                   CarType type, BigDecimal price, Double seatingCapacity) {
        super(brand, model, year, type, price);
        this.seatingCapacity = seatingCapacity;
    }
}

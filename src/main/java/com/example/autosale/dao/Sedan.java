package com.example.autosale.dao;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@Table(name = "sedan")
public class Sedan extends Car{

    @Getter
    @Setter
    @Column(name = "trunk_capacity")
    private Double trunkCapacity;

    public Sedan(String brand, String model,
                 Integer year, CarType type, BigDecimal price, Double trunkCapacity) {
        super(brand, model, year, type, price);
        this.trunkCapacity = trunkCapacity;
    }
}

package com.example.autosale.dao;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue("TRUCK")
public class Truck extends Car{

    @Column(name = "load_capacity")
    private Double loadCapacity;

    public Truck() {
    }

    public Truck(Long id, String brand, String model,
                 Integer year, CarType type, BigDecimal price) {
        super(id, brand, model, year, type, price);
    }

    public Double getLoadCapacity() {
        return loadCapacity;
    }

    public void setLoadCapacity(Double loadCapacity) {
        this.loadCapacity = loadCapacity;
    }
}

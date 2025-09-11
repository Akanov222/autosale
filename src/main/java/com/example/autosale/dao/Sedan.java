package com.example.autosale.dao;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue("SEDAN")
public class Sedan extends Car{

    @Column(name = "trunk_capacity")
    private Double trunkCapacity;

    public Sedan() {
    }

    public Sedan(String brand, String model,
                 Integer year, CarType type, BigDecimal price) {
        super(brand, model, year, type, price);
    }

    public Double getTrunkCapacity() {
        return trunkCapacity;
    }

    public void setTrunkCapacity(Double trunkCapacity) {
        this.trunkCapacity = trunkCapacity;
    }
}

package com.example.autosale.dao;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue("MINIVAN")
public class Minivan extends Car {

    @Column(name = "seating_capacity")
    private Double seatingCapacity;

    public Minivan() {
    }

    public Minivan(Long id, String brand, String model, Integer year,
                   CarType type, BigDecimal price, Double seatingCapacity) {
        super(id, brand, model, year, type, price);
        this.seatingCapacity = seatingCapacity;
    }

    public Double getSeatingCapacity() {
        return seatingCapacity;
    }

    public void setSeatingCapacity(Double seatingCapacity) {
        this.seatingCapacity = seatingCapacity;
    }
}

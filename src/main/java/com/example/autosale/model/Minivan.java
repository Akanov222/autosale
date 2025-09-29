package com.example.autosale.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@Table(name = "minivan")
public class Minivan extends Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Column(name = "seating_capacity")
    private Double seatingCapacity;

    public Minivan(String brand, String model, Integer year,
                   Long carTypeId, BigDecimal price, Double seatingCapacity) {
        super(brand, model, year, carTypeId, price);
        this.seatingCapacity = seatingCapacity;
    }
}

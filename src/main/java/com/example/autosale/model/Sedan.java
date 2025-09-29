package com.example.autosale.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "sedan")
@NoArgsConstructor
public class Sedan extends Car{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Column(name = "trunk_capacity")
    private Double trunkCapacity;

    public Sedan(String brand, String model,
                 Integer year, Long carTypeId, BigDecimal price, Double trunkCapacity) {
        super(brand, model, year, carTypeId, price);
        this.trunkCapacity = trunkCapacity;
    }
}

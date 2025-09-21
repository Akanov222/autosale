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

//    @Getter
//    @Setter
//    @JoinColumn(name = "car_type_id")
//    private Long carTypeId;

    @Getter
    @Setter
    @Column(name = "trunk_capacity")
    private Double trunkCapacity;

    public Sedan(String brand, String model,
                 Integer year, Long carTypeId, BigDecimal price, Double trunkCapacity) {
        super(brand, model, year, carTypeId, price);
//        this.carTypeId = carTypeId;
        this.trunkCapacity = trunkCapacity;
    }
}

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

//    @Getter
//    @Setter
//    @JoinColumn(name = "car_type_id")
//    private Long carTypeId;

    @Getter
    @Setter
    @Column(name = "load_capacity")
    private Double loadCapacity;

    public Truck(String brand, String model,
                 Integer year, Long carTypeId, BigDecimal price, Double loadCapacity) {
        super(brand, model, year, carTypeId, price);
//        this.carTypeId = carTypeId;
        this.loadCapacity = loadCapacity;
    }
}

package com.autosale.dao;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@Table(name = "minivan")
public class Minivan extends Car {

//    @Getter
//    @Setter
//    @JoinColumn(name = "car_type_id")
//    private Long carTypeId;

    @Getter
    @Setter
    @Column(name = "seating_capacity")
    private Double seatingCapacity;

    public Minivan(String brand, String model, Integer year,
                   Long carTypeId, BigDecimal price, Double seatingCapacity) {
        super(brand, model, year, carTypeId, price);
//        super(brand, model, year, price);
//        this.carTypeId = carTypeId;
        this.seatingCapacity = seatingCapacity;
    }
}

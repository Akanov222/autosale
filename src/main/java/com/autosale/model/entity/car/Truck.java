package com.autosale.model.entity.car;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@Table(name = "truck")
public class Truck extends Car{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Column(name = "load_capacity")
    private Double loadCapacity;

    @Getter
    @Setter
    @Column(name = "car_type_name", nullable = false)
    private String carTypeName;

    public Truck(String brand, String model,
                 Integer year, CarType carType, BigDecimal price, Double loadCapacity) {
        super(brand, model, year, carType, price);
        this.loadCapacity = loadCapacity;
    }
}

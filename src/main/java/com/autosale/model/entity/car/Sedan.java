package com.autosale.model.entity.car;

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

    @Getter
    @Setter
    @Column(name = "car_type_name", nullable = false)
    private String carTypeName;

    public Sedan(String brand, String model,
                 Integer year, CarType carType, BigDecimal price, Double trunkCapacity) {
        super(brand, model, year, carType, price);
        this.trunkCapacity = trunkCapacity;
    }
}

package com.example.autosale.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class CarType extends BaseEntity{
//    SEDAN,
//    TRUCK,
//    MINIVAN

    @ManyToOne
    private CarType carType;
}

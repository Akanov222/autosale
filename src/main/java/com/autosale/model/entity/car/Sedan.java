package com.autosale.model.entity.car;

import com.autosale.model.enums.CarTypeEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "sedan")
@NoArgsConstructor
public class Sedan extends Car{

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    @Getter
    @Setter
    @Column(name = "trunk_capacity")
    private Double trunkCapacity;

    public Sedan(Long id, String brand, String model,
                 Integer year, CarType carType, BigDecimal price, Double trunkCapacity) {
        super(id, brand, model, year, carType, price);
        this.trunkCapacity = trunkCapacity;
    }

    @Override
    public String getCarTypeName() {
        return CarTypeEnum.SEDAN.getCode();
    }
}

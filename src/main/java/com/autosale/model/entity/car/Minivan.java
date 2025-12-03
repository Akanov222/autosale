package com.autosale.model.entity.car;

import com.autosale.model.enums.CarTypeEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "minivan")
@NoArgsConstructor
public class Minivan extends Car {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    @Getter
    @Setter
    @Column(name = "seating_capacity")
    private Double seatingCapacity;

    public Minivan(Long id, String brand, String model,
                   Integer year, CarType carType, BigDecimal price, Double seatingCapacity) {
        super(id, brand, model, year, carType, price);
        this.seatingCapacity = seatingCapacity;
    }

    @Override
    public String getCarTypeName() {
        return CarTypeEnum.MINIVAN.getCode();
    }
}

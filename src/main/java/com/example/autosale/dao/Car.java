package com.example.autosale.dao;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name = "car_type")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Car extends BaseEntity{

    @NotBlank(message = "Brand can not be blank!")
    @Column(nullable = false)
    private String brand;

    @NotBlank(message = "Model can not be blank!")
    @Column(nullable = false)
    private String model;

    @NotNull(message = "Year can not ba blank!")
    @Column(nullable = false)
    private Integer year;

//    @Enumerated(EnumType.STRING)
    @OneToOne
    @JoinColumn(name = "car_type")
    private CarType type;

    @PositiveOrZero(message = "Price must be positive or zero!")
    @Column(nullable = false)
    private BigDecimal price;
}

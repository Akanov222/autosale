package com.example.autosale.dto;

import com.example.autosale.dao.CarType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MinivanRequest extends CarRequest {
    String brand;
    String model;
    Integer year;
    CarType type;
    BigDecimal price;

    @NotNull(message = "Seating capacity is required")
    @PositiveOrZero(message = "Seating capacity must be positive")
    Double seatingCapacity;
}


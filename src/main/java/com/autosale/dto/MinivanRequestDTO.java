package com.autosale.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class MinivanRequestDTO extends CarRequestDTO {

//    @NotNull(message = "Seating capacity is required")
//    @PositiveOrZero(message = "Seating capacity must be positive")
    private Double seatingCapacity;

    public MinivanRequestDTO(String brand, String model, Integer year,
                             String carTypeName, BigDecimal price,
                             Double seatingCapacity) {
        super(brand, model, year, carTypeName, price);
        this.seatingCapacity = seatingCapacity;
    }
}

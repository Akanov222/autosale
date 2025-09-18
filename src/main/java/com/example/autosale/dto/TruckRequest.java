package com.example.autosale.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TruckRequest extends CarRequest {
        @NotNull(message = "Load capacity is required")
        @PositiveOrZero(message = "Load capacity must be positive")
        Double loadCapacity;
}

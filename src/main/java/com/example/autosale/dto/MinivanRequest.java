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
public class MinivanRequest extends CarRequest {
    @NotNull(message = "Seating capacity is required")
    @PositiveOrZero(message = "Seating capacity must be positive")
    Double seatingCapacity;
}


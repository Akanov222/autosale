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
public class SedanRequest extends CarRequest {
       @NotNull(message = "Trunk capacity is required")
       @PositiveOrZero(message = "Trunk capacity must be positive")
       Double trunkCapacity;
}

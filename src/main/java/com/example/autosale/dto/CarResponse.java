package com.example.autosale.dto;

public record CarResponse(
        Integer id,
        String brand,
        String model,
        double price
) {
}

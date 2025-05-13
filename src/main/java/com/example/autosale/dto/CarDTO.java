package com.example.autosale.dto;

public record CarDTO(
    String type,
    String brand,
    int year,
    String color,
    int trunkCapasity,
    double maxLoad,
    boolean isConvertible
) {}

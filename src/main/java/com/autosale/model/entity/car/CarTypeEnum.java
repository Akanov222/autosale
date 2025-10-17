package com.autosale.model.entity.car;

import jakarta.validation.constraints.NotBlank;

import java.util.Arrays;

public enum CarTypeEnum {
    SEDAN("SEDAN"),
    TRUCK("TRUCK"),
    MINIVAN("MINIVAN");

    private final String code;


    public String getCode() {
        return code;
    }

    CarTypeEnum(String code) {
        this.code = code;
    }

    public static CarTypeEnum fromString(String code) {
        return Arrays.stream(CarTypeEnum.values())
                .filter(t -> t.code.equals(code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown car type code: " + code));
    }
}


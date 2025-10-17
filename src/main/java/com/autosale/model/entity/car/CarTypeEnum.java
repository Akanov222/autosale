package com.autosale.model.entity.car;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

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

    public static CarTypeEnum fromCode(String code) {
        return Arrays.stream(CarTypeEnum.values())
                .filter(t -> t.code.equals(code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown car type code: " + code));


/*        for (CarTypeEnum type: values()) {
            if (type.getCode().equalsIgnoreCase(code)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown car type code: " + code);*/
    }
}


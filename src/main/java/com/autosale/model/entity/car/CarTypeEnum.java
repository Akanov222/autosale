package com.autosale.model.entity.car;

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
        for (CarTypeEnum type: values()) {
            if (type.getCode().equalsIgnoreCase(code)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown car type code: " + code);
    }
}


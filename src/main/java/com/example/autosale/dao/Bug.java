package com.example.autosale.dao;

public class Bug extends Auto {

    private boolean isConvertible;

    public Bug(String brand, int year, String color, boolean isConvertible) {
        super(brand, year, color);
        this.isConvertible = isConvertible;
    }

    public boolean isConvertible() {
        return isConvertible;
    }

    public void setConvertible(boolean convertible) {
        isConvertible = convertible;
    }
}

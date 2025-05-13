package com.example.autosale.dao;

public class Auto extends Vehicle {

    private String color;

    public Auto(String brand, int year, String color) {
        super(brand, year);
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}

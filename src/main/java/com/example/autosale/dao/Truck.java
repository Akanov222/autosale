package com.example.autosale.dao;

public class Truck extends Auto {

    private double maxLoad;

    public Truck(String brand, int year, String color, double maxLoad) {
        super(brand, year, color);
        this.maxLoad = maxLoad;
    }

    public double getMaxLoad() {
        return maxLoad;
    }

    public void setMaxLoad(double maxLoad) {
        this.maxLoad = maxLoad;
    }
}

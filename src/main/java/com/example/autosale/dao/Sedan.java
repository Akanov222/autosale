package com.example.autosale.dao;

public class Sedan extends Auto {

    private int trunkCapasity;

    public Sedan(String brand, int year, String color, int trunkCapasity) {
        super(brand, year, color);
        this.trunkCapasity = trunkCapasity;
    }

    public int getTrunkCapasity() {
        return trunkCapasity;
    }

    public void setTrunkCapasity(int trunkCapasity) {
        this.trunkCapasity = trunkCapasity;
    }
}

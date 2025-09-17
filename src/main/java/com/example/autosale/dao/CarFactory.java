package com.example.autosale.dao;

import com.example.autosale.dto.CarRequest;

public class CarFactory {

    public Car createCar(String type, CarRequest request) {
        return switch (type.toUpperCase()) {
            case "SEDAN" -> {
                SedanRequest sedanRequest = (SedanRequest) request;
                Sedan sedan = new Sedan();
                setCommonFields(request, sedan);
                sedan.setTrunkCapacity(sedanRequest.trunkCapacity);
                yield sedan;
            }
            case "MINIVAN" -> {
                MinivanRequest minivanRequest = (MinivanRequest) request;
                Minivan minivan = new Minivan();
                setCommonFields(request, minivan);
                minivan.setSeatingCapacity(minivanRequest.seatingCapacity);
                yield minivan;
            }
            case "TRUCK" -> {
                TruckRequest truckRequest = (TruckRequest) request;
                Truck truck = new Truck()
                setCommonFields(request, truck);
                truck.setLoadCapacity(truckRequest.loadCapacity);
                yield truck;
            }
        };
    }

    private void setCommonFields(CarRequest request, Car car) {
        car.setBrand(request.brand());
        car.setModel(request.model());
        car.setYear(request.year());
        car.setPrice(request.price());
    }
}
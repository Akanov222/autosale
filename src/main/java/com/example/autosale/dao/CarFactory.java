package com.example.autosale.dao;

import com.example.autosale.dto.CarRequest;
import com.example.autosale.dto.MinivanRequest;
import com.example.autosale.dto.SedanRequest;
import com.example.autosale.dto.TruckRequest;
import org.springframework.stereotype.Component;

@Component
public class CarFactory {

    public Car createCar(String type, CarRequest request) {
        return switch (type.toUpperCase()) {
            case "SEDAN" -> {
                SedanRequest sedanRequest = (SedanRequest) request;
                Sedan sedan = new Sedan();
                setCommonFields(request, sedan);
                sedan.setTrunkCapacity(sedanRequest.getTrunkCapacity());
                yield sedan;
            }
            case "MINIVAN" -> {
                MinivanRequest minivanRequest = (MinivanRequest) request;
                Minivan minivan = new Minivan();
                setCommonFields(request, minivan);
                minivan.setSeatingCapacity(minivanRequest.getSeatingCapacity());
                yield minivan;
            }
            case "TRUCK" -> {
                TruckRequest truckRequest = (TruckRequest) request;
                Truck truck = new Truck();
                setCommonFields(request, truck);
                truck.setLoadCapacity(truckRequest.getLoadCapacity());
                yield truck;
            }
            default -> throw new IllegalStateException("Unexpected value: " + type.toUpperCase());
        };
    }

    private void setCommonFields(CarRequest request, Car car) {
        car.setBrand(request.getBrand());
        car.setModel(request.getModel());
        car.setYear(request.getYear());
        car.setPrice(request.getPrice());
    }
}
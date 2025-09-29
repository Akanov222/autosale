package com.example.autosale.factory;

import com.example.autosale.dto.CarRequest;
import com.example.autosale.model.Car;
import com.example.autosale.model.Truck;
import org.springframework.stereotype.Component;

@Component
public class TruckFactory implements CarFactory {

    @Override
    public Car createCar(String type, CarRequest request) {
        if (!"truck".equalsIgnoreCase(type)) {
            throw new IllegalArgumentException("TruckFactory can only create trucks");
        }

        return new Truck(request.getBrand(), request.getModel(),
                request.getYear(),request.getCarTypeId(), request.getModel(),
                request.getPrice(), request.getLoadCapacity());
    }
}

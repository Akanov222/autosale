package com.autosale.factory;

import com.autosale.dto.CarRequest;
import com.autosale.model.Car;
import com.autosale.model.Minivan;
import org.springframework.stereotype.Component;

@Component
public class MinivanFactory implements CarFactory{

    @Override
    public Car createCar(String type, CarRequest request) {
        if (!"minivan".equalsIgnoreCase(type)) {
            throw new IllegalArgumentException("MinivanFactory can only create minivans");
        }

        return new Minivan(request.getBrand(), request.getModel(),
                request.getYear(),request.getCarTypeId(), request.getModel(),
                request.getPrice(), request.getSeatingCapacity());
    }
}

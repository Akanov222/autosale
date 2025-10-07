package com.autosale.factory;

import com.autosale.dto.CarRequest;
import com.autosale.model.Car;
import com.autosale.model.Sedan;
import org.springframework.stereotype.Component;

@Component
public class SedanFactory implements CarFactory{

    @Override
    public Car createCar(String type, CarRequest request) {
        if (!"sedan".equalsIgnoreCase(type)) {
            throw new IllegalArgumentException("SedanFactory can only create sedans");
        }

        return new Sedan(request.getBrand(), request.getModel(),
                request.getYear(),request.getCarTypeId(), request.getModel(),
                request.getPrice(), request.getTrunkCapacity());
    }
}

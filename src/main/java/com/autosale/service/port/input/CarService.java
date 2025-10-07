package com.autosale.service.port.input;

import com.autosale.model.entity.car.Car;
import org.springframework.stereotype.Service;

@Service
public interface CarService {

    void saveCar(Car car);
    String getType();
    Long getTypeId();
}


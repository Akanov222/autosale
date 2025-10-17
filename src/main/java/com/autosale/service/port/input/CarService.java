package com.autosale.service.port.input;

import com.autosale.model.entity.car.Car;
import org.springframework.stereotype.Service;

@Service
public interface CarService<T extends Car> {

    void saveCar(T car);
    String getType();
//    Long getTypeId();
}


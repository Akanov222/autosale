package com.autosale.service.port.input;

import com.autosale.model.entity.car.Car;
import org.springframework.stereotype.Service;

@Service
public interface CarRepositoryService<T extends Car> {

    void saveCar(T car);
    String getType();
//    Long getTypeId();
}


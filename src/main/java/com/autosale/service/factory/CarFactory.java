package com.autosale.service.factory;

import com.autosale.dto.CarRequestDTO;
import com.autosale.model.entity.car.Car;
import org.springframework.stereotype.Component;

@Component
public interface CarFactory<T extends CarRequestDTO> {

    String getType();
    Car createCar(String type, T dto);
}

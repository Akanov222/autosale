package com.autosale.service.port.input;

import com.autosale.dto.CarResponse;
import com.autosale.model.entity.car.Car;
import org.springframework.stereotype.Service;

@Service
public interface CarRepositoryService<T extends Car> {

    String getType();
    void saveCar(T car);
    void deleteCarById(Long id);
    CarResponse getCarById(Long id);
}


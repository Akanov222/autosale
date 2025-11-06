package com.autosale.service.port.input;

import com.autosale.dto.CarDto;
import com.autosale.model.entity.car.Car;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface CarRepositoryService<T extends Car> {

    String getType();
    void saveCar(T car);
    void deleteCarById(Long id);
    Optional<Car> getCarById(Long id);
}


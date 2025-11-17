package com.autosale.service.port.input;

import com.autosale.dto.CarDto;
import com.autosale.model.entity.car.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface CarRepositoryService<T extends Car> {

    String getType();
    Optional<Car> getCarById(Long id);
    Page<Car> getAllCars(Pageable pageable);
    Optional<Car> saveCar(T car);
    Optional<Car> updateCar(T car);
    void deleteCarById(Long id);
}


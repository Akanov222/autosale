package com.autosale.service.port.input;

import com.autosale.dto.CarSearchCriteria;
import com.autosale.dto.CarUpdateDto;
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
    Optional<Car> partialUpdateCar(Long id, CarUpdateDto updateDto);
    void deleteCarById(Long id);

    Page<Car> searchCars(CarSearchCriteria criteria, Pageable pageable);

}


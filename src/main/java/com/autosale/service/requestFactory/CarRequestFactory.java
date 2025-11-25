package com.autosale.service.requestFactory;

import com.autosale.dto.CarDto;
import com.autosale.dto.CarUpdateDto;
import com.autosale.model.entity.car.Car;
import org.springframework.stereotype.Component;

@Component
public interface CarRequestFactory<T extends CarDto> {

    String getType();
    Car createCar(String type, T dto);
//    Car updateCarFromDto(Car existingCar, CarUpdateDto updateDto);
}

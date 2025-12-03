package com.autosale.service.responseFactory;

import com.autosale.dto.CarDto;
import com.autosale.dto.CarResponseDto;
import com.autosale.model.entity.car.Car;
import org.springframework.stereotype.Component;

@Component
public interface CarResponseFactory<T extends Car> {

    String getType();
    CarResponseDto createCarDto(String type, T car);
    CarDto carToDto(String type, T car);
}

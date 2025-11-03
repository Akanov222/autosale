package com.autosale.service.responseFactory;

import com.autosale.dto.CarDto;
import com.autosale.model.entity.car.Car;
import org.springframework.stereotype.Component;

@Component
public interface CarResponseFactory<T extends Car> {

    String getType();
    CarDto createCarDto(String type, T car);
}

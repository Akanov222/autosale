package com.example.autosale.mapper;

import com.example.autosale.dto.CarRequest;
import com.example.autosale.dto.CarResponse;
import com.example.autosale.model.Car;
import org.mapstruct.Mapper;

@Mapper
public interface CarMapper {

    CarResponse carToCarResponse(Car car);
    Car carRequestToCar(CarRequest request);
}

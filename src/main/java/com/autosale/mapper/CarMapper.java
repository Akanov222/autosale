package com.autosale.mapper;

import com.autosale.dto.CarRequestDTO;
import com.autosale.dto.CarResponse;
import com.autosale.model.entity.car.Car;
import org.mapstruct.Mapper;

@Mapper
public interface CarMapper {

    CarResponse carToCarResponse(Car car);
    Car carRequestToCar(CarRequestDTO request);
}

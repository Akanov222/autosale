package com.autosale.service.responseFactory;

import com.autosale.dto.CarDto;
import com.autosale.dto.CarResponseDto;
import com.autosale.dto.TruckDto;
import com.autosale.dto.TruckResponseDto;
import com.autosale.model.entity.car.*;
import com.autosale.model.enums.CarTypeEnum;
import com.autosale.repository.factory.CarTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TruckResponseFactory implements CarResponseFactory {

    private final CarTypeRepository repository;

    @Autowired
    public TruckResponseFactory(CarTypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public String getType() {
        return CarTypeEnum.TRUCK.getCode();
    }

    @Override
    public CarResponseDto createCarDto(String type, Car car) {
        CarTypeEnum carTypeEnum = CarTypeEnum.TRUCK;
        if (!carTypeEnum.getCode().equalsIgnoreCase(type)) {
            throw new IllegalArgumentException("SedanFactory can only create sedans");
        }

        CarType carType = repository.findByName(carTypeEnum.getCode())
                .orElseThrow(() -> new IllegalArgumentException("CarType not found"));

        TruckResponseDto truckDto = new TruckResponseDto();
        truckDto.setBrand(car.getBrand());
        truckDto.setModel(car.getModel());
        truckDto.setYear(car.getYear());
        truckDto.setCarTypeName(carType);
        truckDto.setPrice(car.getPrice());
        truckDto.setLoadCapacity(((Truck)car).getLoadCapacity());
        return truckDto;
    }
}

package com.autosale.service.responseFactory;

import com.autosale.dto.CarDto;
import com.autosale.dto.SedanDto;
import com.autosale.model.entity.car.Car;
import com.autosale.model.entity.car.CarType;
import com.autosale.model.entity.car.CarTypeEnum;
import com.autosale.model.entity.car.Sedan;
import com.autosale.repository.factory.CarTypeRepository;
import org.springframework.stereotype.Component;

@Component
public class SedanResponseFactory implements CarResponseFactory {

    private final CarTypeRepository repository;

    public SedanResponseFactory(CarTypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public String getType() {
        return CarTypeEnum.SEDAN.getCode();
    }

    @Override
    public CarDto createCarDto(String type, Car car) {
        CarTypeEnum carTypeEnum = CarTypeEnum.SEDAN;
        if (!carTypeEnum.getCode().equalsIgnoreCase(type)) {
            throw new IllegalArgumentException("SedanFactory can only create sedans");
        }

        CarType carType = repository.findByName(carTypeEnum.getCode())
                .orElseThrow(() -> new IllegalArgumentException("CarType not found"));

        SedanDto sedanDto = new SedanDto();
        sedanDto.setBrand(car.getBrand());
        sedanDto.setModel(car.getModel());
        sedanDto.setYear(car.getYear());
        sedanDto.setCarTypeName(carType.getName());
        sedanDto.setPrice(car.getPrice());
        sedanDto.setTrunkCapacity(((Sedan)car).getTrunkCapacity());
        return sedanDto;
    }
}

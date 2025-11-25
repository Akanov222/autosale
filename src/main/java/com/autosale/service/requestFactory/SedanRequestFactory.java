package com.autosale.service.requestFactory;

import com.autosale.dto.CarDto;
import com.autosale.dto.SedanDto;
import com.autosale.model.entity.car.Car;
import com.autosale.model.entity.car.CarType;
import com.autosale.model.enums.CarTypeEnum;
import com.autosale.model.entity.car.Sedan;
import com.autosale.repository.factory.CarTypeRepository;
import org.springframework.stereotype.Component;

@Component
public class SedanRequestFactory implements CarRequestFactory {

    private final CarTypeRepository repository;

    public SedanRequestFactory(CarTypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public String getType() {
        return CarTypeEnum.SEDAN.getCode();
    }

    @Override
    public Car createCar(String type, CarDto carDto) {
        CarTypeEnum carTypeEnum = CarTypeEnum.SEDAN;
        if (!carTypeEnum.getCode().equalsIgnoreCase(type)) {
            throw new IllegalArgumentException("SedanFactory can only create sedans");
        }

        CarType carType = repository.findByName(carTypeEnum.getCode())
                .orElseThrow(() -> new IllegalArgumentException("CarType not found"));

        Sedan sedan = new Sedan();
        sedan.setBrand(carDto.getBrand());
        sedan.setModel(carDto.getModel());
        sedan.setYear(carDto.getYear());
        sedan.setCarType(carType);
        sedan.setPrice(carDto.getPrice());
        sedan.setTrunkCapacity(((SedanDto)carDto).getTrunkCapacity());
        return sedan;
    }
}

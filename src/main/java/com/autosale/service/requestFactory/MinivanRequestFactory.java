package com.autosale.service.requestFactory;

import com.autosale.dto.CarDto;
import com.autosale.dto.MinivanDto;
import com.autosale.model.entity.car.Car;
import com.autosale.model.entity.car.CarType;
import com.autosale.model.entity.car.CarTypeEnum;
import com.autosale.model.entity.car.Minivan;
import com.autosale.repository.factory.CarTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MinivanRequestFactory implements CarRequestFactory {

    private final CarTypeRepository repository;

    @Autowired
    public MinivanRequestFactory(CarTypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public String getType() {
        return CarTypeEnum.MINIVAN.getCode();
    }

    @Override
    public Car createCar(String type, CarDto carDto) {
        CarTypeEnum carTypeEnum = CarTypeEnum.MINIVAN;
        if (!carTypeEnum.getCode().equalsIgnoreCase(type)) {
            throw new IllegalArgumentException("MinivanFactory can only create minivans");
        }

        CarType carType = repository.findByName(carTypeEnum.getCode())
                .orElseThrow(() -> new IllegalArgumentException("CarType not found"));

        Minivan minivan = new Minivan();
        minivan.setBrand(carDto.getBrand());
        minivan.setModel(carDto.getModel());
        minivan.setYear(carDto.getYear());
        minivan.setCarType(carType);
        minivan.setPrice(carDto.getPrice());
        minivan.setSeatingCapacity(((MinivanDto) carDto).getSeatingCapacity());
        return minivan;
    }
}

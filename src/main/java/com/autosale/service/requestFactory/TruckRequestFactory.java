package com.autosale.service.requestFactory;

import com.autosale.dto.CarDto;
import com.autosale.dto.TruckDto;
import com.autosale.model.entity.car.Car;
import com.autosale.model.entity.car.CarType;
import com.autosale.model.enums.CarTypeEnum;
import com.autosale.model.entity.car.Truck;
import com.autosale.repository.factory.CarTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TruckRequestFactory implements CarRequestFactory {

    private final CarTypeRepository repository;

    @Autowired
    public TruckRequestFactory(CarTypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public String getType() {
        return CarTypeEnum.TRUCK.getCode();
    }

    @Override
    public Car createCar(String type, CarDto carDto) {
        CarTypeEnum carTypeEnum = CarTypeEnum.TRUCK;
        if (!carTypeEnum.getCode().equalsIgnoreCase(type)) {
            throw new IllegalArgumentException("TruckFactory can only create trucks");
        }

        CarType carType = repository.findByName(carTypeEnum.getCode())
                .orElseThrow(() -> new IllegalArgumentException("CarType not found"));

        Truck truck = new Truck();
        truck.setBrand(carDto.getBrand());
        truck.setModel(carDto.getModel());
        truck.setYear(carDto.getYear());
        truck.setCarType(carType);
        truck.setPrice(carDto.getPrice());
        truck.setLoadCapacity(((TruckDto) carDto).getLoadCapacity());
        return truck;
    }
}

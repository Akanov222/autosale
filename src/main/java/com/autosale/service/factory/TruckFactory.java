package com.autosale.service.factory;

import com.autosale.dto.CarRequestDTO;
import com.autosale.dto.TruckRequestDTO;
import com.autosale.model.entity.car.Car;
import com.autosale.model.entity.car.CarType;
import com.autosale.model.entity.car.CarTypeEnum;
import com.autosale.model.entity.car.Truck;
import com.autosale.repository.factory.CarTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TruckFactory implements CarFactory {

    private final CarTypeRepository repository;

    @Autowired
    public TruckFactory(CarTypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public String getType() {
        return CarTypeEnum.TRUCK.getCode();
    }

    @Override
    public Car createCar(String type, CarRequestDTO request) {
        CarTypeEnum carTypeEnum = CarTypeEnum.TRUCK;
        if (!carTypeEnum.getCode().equalsIgnoreCase(type)) {
            throw new IllegalArgumentException("TruckFactory can only create trucks");
        }

        CarType carType = repository.findByName(carTypeEnum.getCode())
                .orElseThrow(() -> new IllegalArgumentException("CarType not found"));

        Truck truck = new Truck();
        truck.setBrand(request.getBrand());
        truck.setModel(request.getModel());
        truck.setYear(request.getYear());
        truck.setCarType(carType);
        truck.setPrice(request.getPrice());
        truck.setLoadCapacity(((TruckRequestDTO) request).getLoadCapacity());
        return truck;
    }
}

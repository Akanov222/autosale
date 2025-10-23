package com.autosale.service.port.input;

import com.autosale.model.entity.car.Car;
import com.autosale.model.entity.car.CarTypeEnum;
import com.autosale.model.entity.car.Truck;
import com.autosale.repository.factory.TruckRepository;
import org.springframework.stereotype.Service;

@Service
public class TruckRepositoryService implements CarRepositoryService {

    private final TruckRepository repository;

    public TruckRepositoryService(TruckRepository repository) {
        this.repository = repository;
    }

    @Override
    public void saveCar(Car car) {
        Truck truck = (Truck) car;
        repository.save(truck);
    }

    @Override
    public String getType() {
        return CarTypeEnum.TRUCK.getCode();
    }
}

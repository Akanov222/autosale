package com.autosale.service.port.input;

import com.autosale.model.entity.car.Car;
import com.autosale.model.entity.car.CarTypeEnum;
import com.autosale.model.entity.car.Truck;
import com.autosale.repository.factory.TruckRepository;
import org.springframework.stereotype.Service;

@Service
public class TruckRepositoryService implements CarRepositoryService {

    private final TruckRepository truckRepository;

    public TruckRepositoryService(TruckRepository truckRepository) {
        this.truckRepository = truckRepository;
    }

    @Override
    public void saveCar(Car car) {
        Truck truck = (Truck) car;
        truckRepository.save(truck);
    }

    @Override
    public void deleteCarById(Long id) {
        truckRepository.deleteById(id);
    }

    @Override
    public Car getCarById(Long id) {
        return truckRepository.getReferenceById(id);
    }

    @Override
    public String getType() {
        return CarTypeEnum.TRUCK.getCode();
    }
}

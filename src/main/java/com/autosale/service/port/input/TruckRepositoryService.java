package com.autosale.service.port.input;

import com.autosale.model.entity.car.Car;
import com.autosale.model.enums.CarTypeEnum;
import com.autosale.model.entity.car.Truck;
import com.autosale.repository.factory.TruckRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
    public Optional<Car> getCarById(Long id) {
        return truckRepository.findById(id).map(car -> car);
    }

    @Override
    public String getType() {
        return CarTypeEnum.TRUCK.getCode();
    }
}

package com.autosale.service.port.input;

import com.autosale.model.entity.car.Car;
import com.autosale.model.enums.CarTypeEnum;
import com.autosale.model.entity.car.Truck;
import com.autosale.repository.factory.TruckRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TruckRepositoryService implements CarRepositoryService {

    private final TruckRepository truckRepository;

    public TruckRepositoryService(TruckRepository truckRepository) {
        this.truckRepository = truckRepository;
    }

    @Override
    public String getType() {
        return CarTypeEnum.TRUCK.getCode();
    }

    @Override
    public void deleteCarById(Long id) {
        truckRepository.deleteById(id);
    }

    @Override
    public Page<Car> getAllCars(Pageable pageable) {
        return truckRepository.findAll(pageable).map(truck -> truck);
    }

    @Override
    public Optional<Car> saveCar(Car car) {
        Truck truck = (Truck) car;
        return Optional.of(truckRepository.save(truck));
    }

    @Override
    public Optional<Car> updateCar(Car car) {
        Truck truck = (Truck) car;
        if(!truckRepository.existsById(truck.getId())) {
            return Optional.empty();
        }
        return Optional.of(truckRepository.save(truck));
    }

    @Override
    public Optional<Car> getCarById(Long id) {
        return truckRepository.findById(id).map(car -> car);
    }
}

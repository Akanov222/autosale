package com.autosale.service.port.input;

import com.autosale.dto.CarSearchCriteria;
import com.autosale.dto.CarUpdateDto;
import com.autosale.dto.TruckUpdateDto;
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
    public Page<Car> searchCars(CarSearchCriteria criteria, Pageable pageable) {
        return truckRepository.searchTrucks(criteria, pageable).map(truck -> truck);
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
    public Optional<Car> partialUpdateCar(Long id, CarUpdateDto updateDto) {
        Optional<Truck> existingCarOpt = truckRepository.findById(id);

        if (existingCarOpt.isEmpty()) {
            return Optional.empty();
        }

        Truck existingCar = existingCarOpt.get();
        if (updateDto.getBrand() != null) {
            existingCar.setBrand(updateDto.getBrand());
        }
        if (updateDto.getModel() != null) {
            existingCar.setModel(updateDto.getModel());
        }
        if (updateDto.getYear() != null) {
            existingCar.setYear(updateDto.getYear());
        }
        if (updateDto.getPrice() != null) {
            existingCar.setPrice(updateDto.getPrice());
        }

        if (updateDto instanceof TruckUpdateDto truckUpdate) {
            if (truckUpdate.getLoadCapacity() != null) {
                existingCar.setLoadCapacity(truckUpdate.getLoadCapacity());
            }
        }
        Truck savedCar = truckRepository.save(existingCar);
        return Optional.of(savedCar);
    }

    @Override
    public Optional<Car> getCarById(Long id) {
        return truckRepository.findById(id).map(car -> car);
    }
}

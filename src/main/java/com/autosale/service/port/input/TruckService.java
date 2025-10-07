package com.autosale.service;

import com.autosale.model.Car;
import com.autosale.model.Truck;
import com.autosale.repository.car.TruckRepository;
import org.springframework.stereotype.Service;

@Service
public class TruckService implements CarService{

    private final TruckRepository repository;

    public TruckService(TruckRepository repository) {
        this.repository = repository;
    }

    @Override
    public void saveCar(Car car) {
        repository.save((Truck) car);
    }

    @Override
    public String getType() {
        return "truck";
    }
}

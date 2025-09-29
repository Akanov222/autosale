package com.example.autosale.service;

import com.example.autosale.model.Car;
import com.example.autosale.model.Truck;
import com.example.autosale.repository.TruckRepository;
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

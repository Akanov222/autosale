package com.autosale.service;

import com.autosale.model.Car;
import com.autosale.model.Minivan;
import com.autosale.repository.car.MinivanRepository;
import org.springframework.stereotype.Service;

@Service
public class MinivanService implements CarService {

    private final MinivanRepository repository;

    public MinivanService(MinivanRepository repository) {
        this.repository = repository;
    }

    @Override
    public void saveCar(Car car) {
        repository.save((Minivan) car);
    }

    @Override
    public String getType() {
        return "minivan";
    }
}

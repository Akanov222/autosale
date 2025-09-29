package com.example.autosale.service;

import com.example.autosale.model.Car;
import com.example.autosale.model.Minivan;
import com.example.autosale.repository.MinivanRepository;
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

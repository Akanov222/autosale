package com.example.autosale.service;

import com.example.autosale.dao.CarType;
import com.example.autosale.repository.CarTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarTypeService {

    private final CarTypeRepository carTypeRepository;

    @Autowired
    public CarTypeService(CarTypeRepository carTypeRepository) {
        this.carTypeRepository = carTypeRepository;
    }

    public CarType getCarTypeByName(String name) {
        return carTypeRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("CarType with name " + name + " not found"));
    }
}

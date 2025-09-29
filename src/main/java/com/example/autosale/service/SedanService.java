package com.example.autosale.service;

import com.example.autosale.model.Car;
import com.example.autosale.model.Sedan;
import com.example.autosale.repository.SedanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SedanService implements CarService {

    public final SedanRepository sedanRepository;

    @Autowired
    public SedanService(SedanRepository sedanRepository) {
        this.sedanRepository = sedanRepository;
    }

    @Override
    public void saveCar(Car car) {
        if (!(car instanceof Sedan)) {
            throw new IllegalArgumentException("Car must be a Sedan");
        }
        sedanRepository.save((Sedan) car);
    }

    @Override
    public String getType() {
        return "sedan";
    }
}

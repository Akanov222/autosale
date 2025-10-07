package com.autosale.service;

import com.autosale.model.Car;
import com.autosale.model.Sedan;
import com.autosale.repository.car.SedanRepository;
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

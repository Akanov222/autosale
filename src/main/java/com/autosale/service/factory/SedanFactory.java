package com.autosale.service.factory;

import com.autosale.dto.CarRequestDTO;
import com.autosale.dto.SedanRequestDTO;
import com.autosale.model.entity.car.Car;
import com.autosale.model.entity.car.Sedan;
import com.autosale.repository.car.CarTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SedanFactory implements CarFactory {

    private final CarTypeRepository repository;

    @Autowired
    public SedanFactory(CarTypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public String getType() {
        return "SEDAN";
    }

    @Override
    public Car createCar(String type, CarRequestDTO request) {
        if (!"SEDAN".equalsIgnoreCase(type)) {
            throw new IllegalArgumentException("SedanFactory can only create sedans");
        }

        Sedan sedan = new Sedan();
        sedan.setBrand(request.getBrand());
        sedan.setModel(request.getModel());
        sedan.setYear(request.getYear());
        sedan.setCarType(repository.getReferenceById(1L));
        sedan.setPrice(request.getPrice());
        sedan.setTrunkCapacity(((SedanRequestDTO) request).getTrunkCapacity());
        return sedan;
    }
}

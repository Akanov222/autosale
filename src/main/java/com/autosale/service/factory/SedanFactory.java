package com.autosale.service.factory;

import com.autosale.dto.CarRequestDTO;
import com.autosale.dto.SedanRequestDTO;
import com.autosale.model.entity.car.Car;
import com.autosale.model.entity.car.CarType;
import com.autosale.model.entity.car.Sedan;
import com.autosale.repository.car.CarTypeRepository;
import com.autosale.repository.car.SedanRepository;
import org.springframework.stereotype.Component;

@Component
public class SedanFactory implements CarFactory<SedanRequestDTO> {

    private final CarTypeRepository repository;

    public SedanFactory(CarTypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public String getType() {
        return "SEDAN";
    }

    @Override
    public Car createCar(String type, SedanRequestDTO sedanRequestDTO) {
        if (!"SEDAN".equalsIgnoreCase(type)) {
            throw new IllegalArgumentException("SedanFactory can only create sedans");
        }

        CarType carType = repository.findByName("SEDAN")
                .orElseThrow(() -> new IllegalArgumentException("CarType not found"));

        Sedan sedan = new Sedan();
        sedan.setBrand(sedanRequestDTO.getBrand());
        sedan.setModel(sedanRequestDTO.getModel());
        sedan.setYear(sedanRequestDTO.getYear());
        sedan.setCarType(carType);
        sedan.setCarTypeName(carType.getName());
        sedan.setPrice(sedanRequestDTO.getPrice());
        sedan.setTrunkCapacity(sedanRequestDTO.getTrunkCapacity());
        System.out.println("SedanFactory " + sedanRequestDTO.getModel());
        System.out.println("SedanFactory " + sedanRequestDTO.getBrand());
        System.out.println("SedanFactory " + sedanRequestDTO.getYear());
        System.out.println("SedanFactory " + sedanRequestDTO.getPrice());
        System.out.println("SedanFactory " + sedanRequestDTO.getTrunkCapacity());
        System.out.println("SedanFactory " + sedan.getCarType().getName());
        System.out.println("SedanFactory " + sedan.getCarType().getId());
        return sedan;
    }
}

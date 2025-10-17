package com.autosale.service.factory;

import com.autosale.dto.SedanRequestDTO;
import com.autosale.model.entity.car.Car;
import com.autosale.model.entity.car.CarType;
import com.autosale.model.entity.car.CarTypeEnum;
import com.autosale.model.entity.car.Sedan;
import com.autosale.repository.car.CarTypeRepository;
import org.springframework.stereotype.Component;

@Component
public class SedanFactory implements CarFactory<SedanRequestDTO> {

    private final CarTypeRepository repository;

    public SedanFactory(CarTypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public String getType() {
        return CarTypeEnum.SEDAN.getCode();
    }

    @Override
    public Car createCar(String type, SedanRequestDTO sedanRequestDTO) {
        CarTypeEnum carTypeEnum = CarTypeEnum.SEDAN;
        if (!carTypeEnum.getCode().equalsIgnoreCase(type)) {
            throw new IllegalArgumentException("SedanFactory can only create sedans");
        }
        CarType carType = repository.findByName(carTypeEnum.getCode())
                .orElseThrow(() -> new IllegalArgumentException("CarType not found"));
        Sedan sedan = new Sedan();
        sedan.setBrand(sedanRequestDTO.getBrand());
        sedan.setModel(sedanRequestDTO.getModel());
        sedan.setYear(sedanRequestDTO.getYear());
        sedan.setCarType(carType);
        sedan.setPrice(sedanRequestDTO.getPrice());
        sedan.setTrunkCapacity(sedanRequestDTO.getTrunkCapacity());
        return sedan;
    }
}

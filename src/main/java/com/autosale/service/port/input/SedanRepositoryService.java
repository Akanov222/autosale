package com.autosale.service.port.input;

import com.autosale.dto.CarResponse;
import com.autosale.model.entity.car.Car;
import com.autosale.model.entity.car.CarTypeEnum;
import com.autosale.model.entity.car.Sedan;
import com.autosale.repository.factory.SedanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SedanRepositoryService implements CarRepositoryService<Sedan> {

    private final SedanRepository sedanRepository;

    @Autowired
    public SedanRepositoryService(SedanRepository sedanRepository) {
        this.sedanRepository = sedanRepository;
    }

    @Override
    public void saveCar(Sedan sedan) {
        sedanRepository.save(sedan);
    }

    @Override
    public void deleteCarById(Long id) {
        sedanRepository.deleteById(id);
    }

    @Override
    public CarResponse getCarById(Long id) {
        Car sedan = new Sedan();
        sedan = sedanRepository.getReferenceById(id);
        return CarResponse.fromCar(sedan);
    }

    @Override
    public String getType() {
        return CarTypeEnum.SEDAN.getCode();
    }
}

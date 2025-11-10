package com.autosale.service.port.input;

import com.autosale.model.entity.car.Car;
import com.autosale.model.enums.CarTypeEnum;
import com.autosale.model.entity.car.Sedan;
import com.autosale.repository.factory.SedanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SedanRepositoryService implements CarRepositoryService<Sedan> {

    private final SedanRepository sedanRepository;

    @Autowired
    public SedanRepositoryService(SedanRepository sedanRepository) {
        this.sedanRepository = sedanRepository;
    }

    @Override
    public Optional<Car> getCarById(Long id) {
        return sedanRepository.findById(id).map(car -> car);
    }

    @Override
    public Optional<Car> saveCar(Sedan sedan) {
        return Optional.of(sedanRepository.save(sedan));
    }

    @Override
    public void deleteCarById(Long id) {
        sedanRepository.deleteById(id);
    }

    @Override
    public String getType() {
        return CarTypeEnum.SEDAN.getCode();
    }
}

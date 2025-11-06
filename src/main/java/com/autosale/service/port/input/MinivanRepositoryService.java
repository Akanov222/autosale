package com.autosale.service.port.input;

import com.autosale.model.entity.car.Car;
import com.autosale.model.enums.CarTypeEnum;
import com.autosale.model.entity.car.Minivan;
import com.autosale.repository.factory.MinivanRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MinivanRepositoryService implements CarRepositoryService {

    private final MinivanRepository minivanRepository;

    public MinivanRepositoryService(MinivanRepository minivanRepository) {
        this.minivanRepository = minivanRepository;
    }

    @Override
    public void saveCar(Car car) {
        Minivan minivan = (Minivan) car;
        minivanRepository.save(minivan);
    }

    @Override
    public void deleteCarById(Long id) {
        minivanRepository.deleteById(id);
    }

    @Override
    public Optional<Car> getCarById(Long id) {
        return minivanRepository.findById(id).map(car -> car);
    }

    @Override
    public String getType() {
        return CarTypeEnum.MINIVAN.getCode();
    }
}

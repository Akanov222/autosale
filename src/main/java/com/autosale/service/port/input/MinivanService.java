package com.autosale.service.port.input;

import com.autosale.model.entity.car.Car;
import com.autosale.model.entity.car.CarTypeEnum;
import com.autosale.model.entity.car.Minivan;
import com.autosale.repository.factory.MinivanRepository;
import org.springframework.stereotype.Service;

@Service
public class MinivanService implements CarService {

    private final MinivanRepository repository;

    public MinivanService(MinivanRepository repository) {
        this.repository = repository;
    }

    @Override
    public void saveCar(Car car) {
        Minivan minivan = (Minivan) car;
        repository.save(minivan);
    }

    @Override
    public String getType() {
        return CarTypeEnum.MINIVAN.getCode();
    }
}

package com.autosale.service.port.input;

import com.autosale.model.entity.car.Car;
import com.autosale.model.entity.car.Sedan;
import com.autosale.repository.car.MinivanRepository;
import org.springframework.stereotype.Service;

@Service
public class MinivanService implements CarService {

    private final MinivanRepository repository;

    public MinivanService(MinivanRepository repository) {
        this.repository = repository;
    }

    @Override
    public void saveCar(Car car) {
        repository.save((Sedan.Minivan) car);
    }

    @Override
    public String getType() {
        return "MINIVAN";
    }

    @Override
    public Long getTypeId() {
        return 0L;
    }
}

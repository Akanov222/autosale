package com.autosale.service.factory;

import com.autosale.dto.CarRequestDTO;
import com.autosale.dto.MinivanRequestDTO;
import com.autosale.model.entity.car.Car;
import com.autosale.model.entity.car.CarType;
import com.autosale.model.entity.car.Minivan;
import com.autosale.repository.car.CarTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.autosale.model.entity.car.CarTypeEnum.MINIVAN;

@Component
public class MinivanFactory implements CarFactory {

    private final CarTypeRepository repository;

    @Autowired
    public MinivanFactory(CarTypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public String getType() {
        return MINIVAN.name();
    }

    @Override
    public Car createCar(String type, CarRequestDTO request) {
        if (!"MINIVAN".equalsIgnoreCase(type)) {
            throw new IllegalArgumentException("MinivanFactory can only create minivans");
        }

        CarType carType = repository.findByName(MINIVAN.name())
                .orElseThrow(() -> new IllegalArgumentException("CarType not found"));

        Minivan minivan = new Minivan();
        minivan.setBrand(request.getBrand());
        minivan.setModel(request.getModel());
        minivan.setYear(request.getYear());
        minivan.setCarType(carType);
        minivan.setPrice(request.getPrice());
        minivan.setSeatingCapacity(((MinivanRequestDTO) request).getSeatingCapacity());
        return minivan;

    }
}

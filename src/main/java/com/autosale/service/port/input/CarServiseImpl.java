package com.autosale.service.port.input;

import com.autosale.dto.CarRequestDTO;
import com.autosale.model.entity.car.Car;
import com.autosale.service.factory.CarFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CarServiseImpl {

/*    private final Map<String, CarFactory> factories;
    private final Map<String, CarService> services;

    public CarServiseImpl(List<CarFactory> factories, List<CarService> services) {
        this.factories = factories.stream().collect(Collectors.toMap((CarFactory::getType), f -> f));
        this.services = services.stream().collect(Collectors.toMap((CarService::getType), f -> f));
    }

    public void saveCar(String type, CarRequestDTO carRequestDTO) {
        CarRequestDTO specificDto;
        Car specificCar;

        CarFactory factory = factories.get(type.toUpperCase());
        CarService service = services.get(type.toLowerCase());

    }*/
}

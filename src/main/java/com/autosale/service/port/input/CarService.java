package com.autosale.service.port.input;

import com.autosale.dto.CarRequestDTO;
import com.autosale.model.entity.car.Car;
import com.autosale.service.factory.CarFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CarService {

    private final Map<String, CarFactory> factories;
    private final Map<String, CarRepositoryService> repositoryServices;

    public CarService(List<CarFactory> factories,
                      List<CarRepositoryService> repositoryServices
    ) {
        this.factories = factories.stream().collect(Collectors
                .toMap((CarFactory::getType), f -> f));
        this.repositoryServices = repositoryServices.stream().collect(Collectors
                .toMap((CarRepositoryService::getType), s -> s));
    }

    public void saveCar(String type, CarRequestDTO carRequestDTO) {

        CarFactory factory = factories.get(type.toUpperCase());
        Car specificCar = factory.createCar(type, carRequestDTO);
        CarRepositoryService repositoryService = repositoryServices.get(type.toUpperCase());
        repositoryService.saveCar(specificCar);

    }
}

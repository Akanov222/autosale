package com.autosale.service.port.input;

import com.autosale.dto.CarDto;
import com.autosale.model.entity.car.Car;
import com.autosale.service.requestFactory.CarRequestFactory;
import com.autosale.service.responseFactory.CarResponseFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CarService {

    private final Map<String, CarRequestFactory> requestFactories;
    private final Map<String, CarResponseFactory> responseFactories;
    private final Map<String, CarRepositoryService> repositoryServices;

    public CarService(List<CarRequestFactory> requestFactories,
                      List<CarResponseFactory> responseFactories,
                      List<CarRepositoryService> repositoryServices
    ) {
        this.requestFactories = requestFactories.stream().collect(Collectors
                .toMap((CarRequestFactory::getType), f -> f));
        this.responseFactories = responseFactories.stream().collect(Collectors
                .toMap((CarResponseFactory::getType), f -> f));
        this.repositoryServices = repositoryServices.stream().collect(Collectors
                .toMap((CarRepositoryService::getType), s -> s));
    }

    public void saveCar(String type, CarDto carDTO) {
        CarRequestFactory factory = requestFactories.get(type.toUpperCase());
        Car specificCar = factory.createCar(type, carDTO);
        CarRepositoryService repositoryService = repositoryServices.get(type.toUpperCase());
        repositoryService.saveCar(specificCar);
    }

    public void deleteCar(String type, Long id) {
        CarRepositoryService repositoryService = repositoryServices.get(type.toUpperCase());
        repositoryService.deleteCarById(id);
    }

    public CarDto getCarById(String type, Long id) {
        CarRepositoryService repositoryService = repositoryServices.get(type.toUpperCase());
        CarResponseFactory factory = responseFactories.get(type.toUpperCase());
        Car car = repositoryService.getCarById(id);
        return factory.createCarDto(type, car);
    }
}

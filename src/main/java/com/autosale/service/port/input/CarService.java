package com.autosale.service.port.input;

import com.autosale.dto.CarDto;
import com.autosale.dto.CarResponseDto;
import com.autosale.model.entity.car.Car;
import com.autosale.service.requestFactory.CarRequestFactory;
import com.autosale.service.responseFactory.CarResponseFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
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

    public Optional<CarResponseDto> getCarById(String type, Long id) {
        final String upperCaseType = type.toUpperCase();
        CarRepositoryService repositoryService = repositoryServices.get(upperCaseType);
        CarResponseFactory factory = responseFactories.get(upperCaseType);
        if (repositoryService == null) {
            return Optional.empty();
        }
        Optional<Car> carOptional = repositoryService.getCarById(id);
        return carOptional.flatMap(car -> Optional.ofNullable(factory.createCarDto(type, car)));
    }

    public CarResponseDto saveCar(String type, CarDto carDto) {
        CarRequestFactory factory = requestFactories.get(type.toUpperCase());
        Car specificCar = factory.createCar(type, carDto);
        CarRepositoryService repositoryService = repositoryServices.get(type.toUpperCase());
        repositoryService.saveCar(specificCar);
        CarResponseFactory factoryResponse = responseFactories.get(type.toUpperCase());
        return factoryResponse.createCarDto(type, specificCar);
    }

    public void deleteCar(String type, Long id) {
        CarRepositoryService repositoryService = repositoryServices.get(type.toUpperCase());
        repositoryService.deleteCarById(id);
    }
}

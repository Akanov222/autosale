package com.autosale.service.port.input;

import com.autosale.dto.CarDto;
import com.autosale.dto.CarResponseDto;
import com.autosale.model.entity.car.Car;
import com.autosale.service.requestFactory.CarRequestFactory;
import com.autosale.service.responseFactory.CarResponseFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public Page<CarResponseDto> getAllCars(String type, int page, int size, String sortBy, String direction) {
        final String upperCaseType = type.toUpperCase();
        CarRepositoryService repositoryService = repositoryServices.get(upperCaseType);
        CarResponseFactory factory = responseFactories.get(upperCaseType);
        if (repositoryService == null) {
            return Page.empty();
        }
        Sort sort = Sort.by(Sort.Direction.fromString(direction != null ? direction : "ASC"),
                sortBy != null ? sortBy : "id");
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Car> cars = repositoryService.getAllCars(pageable);
        return cars.map(car -> factory.createCarDto(type, car));
    }

    public Optional<CarResponseDto> saveCar(String type, CarDto carDto) {
        final String upperCaseType = type.toUpperCase();
        CarRequestFactory factory = requestFactories.get(upperCaseType);
        CarRepositoryService repositoryService = repositoryServices.get(upperCaseType);
        CarResponseFactory factoryResponse = responseFactories.get(upperCaseType);
        if (factory == null || repositoryService == null || factoryResponse == null) {
            return Optional.empty();
        }
        Car specificCar = factory.createCar(type, carDto);
        Optional<Car> carOptional = repositoryService.saveCar(specificCar);
        return carOptional.flatMap(car -> Optional.ofNullable(factoryResponse.createCarDto(type, car)));
    }

    public Optional<CarResponseDto> updateCar(String type, Long id, CarDto carDto) {
        final String upperCaseType = type.toUpperCase();
        CarRequestFactory factory = requestFactories.get(upperCaseType);
        CarRepositoryService repositoryService = repositoryServices.get(upperCaseType);
        CarResponseFactory factoryResponse = responseFactories.get(upperCaseType);
        if (factory == null || repositoryService == null || factoryResponse == null) {
            return Optional.empty();
        }
        Car updateCar = factory.createCar(type, carDto);
        updateCar.setId(id);
        Optional<Car> savedCar = repositoryService.updateCar(updateCar);
        return savedCar.flatMap(car -> Optional.ofNullable(factoryResponse.createCarDto(type, car)));
    }


    public boolean deleteCar(String type, Long id) {
        CarRepositoryService repositoryService = repositoryServices.get(type.toUpperCase());
        if (repositoryService == null) {
            return false;
        }
        Optional<Car> carOptional = repositoryService.getCarById(id);
        if (carOptional.isEmpty()) {
            return false;
        }
        repositoryService.deleteCarById(id);
        return true;
    }
}

package com.example.autosale.service;

import com.example.autosale.dao.Car;
import com.example.autosale.dao.CarFactory;
import com.example.autosale.dao.Sedan;
import com.example.autosale.dto.CarRequest;
import com.example.autosale.dto.MinivanRequest;
import com.example.autosale.dto.SedanRequest;
import com.example.autosale.dto.TruckRequest;
import com.example.autosale.repository.GenericCarRepository;
import com.example.autosale.repository.MinivanRepository;
import com.example.autosale.repository.SedanRepository;
import com.example.autosale.repository.TruckRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.type.descriptor.java.StringJavaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CarService {

    private final Map<String, GenericCarRepository<? extends Car>> repositories;
    private final CarFactory carFactory;
    private final ObjectMapper objectMapper;

    public CarService(CarFactory carFactory, ObjectMapper objectMapper,
                      SedanRepository sedanRepository, TruckRepository truckRepository,
                      MinivanRepository minivanRepository) {
        this.repositories = Map.of(
                "SEDAN", sedanRepository,
                "TRUCK", truckRepository,
                "MINIVAN", minivanRepository
        );
        this.carFactory = carFactory;
        this.objectMapper = objectMapper;
    }

    @SuppressWarnings("unchecked")
    public <T extends Car> GenericCarRepository<T> getRepository(String type) {
        return (GenericCarRepository<T>) repositories.get(type.toUpperCase());
    }

    public CarRequest convertRequest(String type, Map<String, Object> payload) {
        Class<? extends CarRequest> requestClass = switch(type.toUpperCase()) {
            case "SEDAN" -> SedanRequest.class;
            case "TRUCK" -> TruckRequest.class;
            case "MINIVAN" -> MinivanRequest.class;
            default -> throw new IllegalArgumentException("Unknown car type: " + type);
        };
        return objectMapper.convertValue(payload, requestClass);
    }
    public ResponseEntity<String> saveCar(String type, Car car) {
        GenericCarRepository<? extends Car> repository = getRepository(type);
//        TODO convert car to SEDAN, TRUCK, MINIVAN
            repository.save(car);
        return ResponseEntity.ok(type.toUpperCase() + " created");
    }

    public ResponseEntity<String> deleteCar(String type, Long id) {
        GenericCarRepository<? extends Car> repository = getRepository(type);
        repository.deleteById(id);
        return ResponseEntity.ok(type.toUpperCase() + " deleted");
    }

    public boolean existsCar(String type, Long id) {
        GenericCarRepository<? extends Car> repository = getRepository(type);
        return repository.existsById(id);
    }
}

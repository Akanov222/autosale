package com.autosale.dao;

import com.autosale.dto.CarRequest;
import com.autosale.dto.MinivanRequest;
import com.autosale.dto.SedanRequest;
import com.autosale.dto.TruckRequest;
import com.autosale.service.CarTypeService;
import org.springframework.stereotype.Component;

@Component
public class CarFactory {

    private final CarTypeService carTypeService;

    public CarFactory(CarTypeService carTypeService) {
        this.carTypeService = carTypeService;
    }

    public Car createCar(String type, CarRequest request) {
        return switch (type.toUpperCase()) {
            case "SEDAN" -> {
                SedanRequest sedanRequest = (SedanRequest) request;
                Sedan sedan = new Sedan();
                setCommonFields(request, sedan);
                sedan.setCarTypeId(carTypeService.getCarTypeByName(type.toUpperCase()).getId());
                sedan.setTrunkCapacity(sedanRequest.getTrunkCapacity());
                yield sedan;
            }
            case "MINIVAN" -> {
                MinivanRequest minivanRequest = (MinivanRequest) request;
                Minivan minivan = new Minivan();
                setCommonFields(request, minivan);
                minivan.setCarTypeId(carTypeService.getCarTypeByName(type.toUpperCase()).getId());
                minivan.setSeatingCapacity(minivanRequest.getSeatingCapacity());
                yield minivan;
            }
            case "TRUCK" -> {
                TruckRequest truckRequest = (TruckRequest) request;
                Truck truck = new Truck();
                setCommonFields(request, truck);
                truck.setCarTypeId(carTypeService.getCarTypeByName(type.toUpperCase()).getId());
                truck.setLoadCapacity(truckRequest.getLoadCapacity());
                yield truck;
            }
            default -> throw new IllegalStateException("Unexpected value: " + type.toUpperCase());
        };
    }

    private void setCommonFields(CarRequest request, Car car) {
        car.setBrand(request.getBrand());
        car.setModel(request.getModel());
        car.setYear(request.getYear());
        car.setPrice(request.getPrice());
    }
}
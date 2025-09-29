package com.example.autosale.factory;

import com.example.autosale.dto.CarRequest;
import com.example.autosale.model.Car;
import org.springframework.stereotype.Component;

@Component
public interface CarFactory {

    Car createCar(String type, CarRequest request);
}
/*

    private final CarTypeService carTypeService;

    public CarFactory(CarTypeService carTypeService) {
        this.carTypeService = carTypeService;
    }

    public <T extends CarRequest> Car createCar(String type, T request) {
        Car car = createCarInstance(type);
        setCommonFields(request, car);
        setSpecificFields(type, request, car);
        return car;
    }

    // TODO CarRequest -> Map<String, Object>
    private void setSpecificFields(String type, CarRequest request, Car car) {
        switch (type.toUpperCase()) {
            case "SEDAN" -> {
                SedanRequest sedanRequest = (SedanRequest) request;
                sedanRequest.setTrunkCapacity(((SedanRequest) request).getTrunkCapacity());
            }
            case "TRUCK" -> {
                TruckRequest truckRequest = (TruckRequest) request;
                truckRequest.setLoadCapacity(((TruckRequest) request).getLoadCapacity());
            }
            case "MINIVAN" -> {
                MinivanRequest minivanRequest = (MinivanRequest) request;
                minivanRequest.setSeatingCapacity(((MinivanRequest) request).getSeatingCapacity());
            }
        }
    }

    private Car createCarInstance(String type) {
        return switch (type.toUpperCase()) {
            case "SEDAN" -> new Sedan();
            case "TRUCK" -> new Truck();
            case "MINIVAN" -> new Minivan();
            default -> throw new IllegalArgumentException("Unknown car type: " + type);
        };
    }

    private void setCommonFields(CarRequest request, Car car) {
        car.setBrand(request.getBrand());
        car.setModel(request.getModel());
        car.setYear(request.getYear());
        car.setPrice(request.getPrice());
    }
}
*/



/*

    public Car createCar2(String type, CarRequest request) {
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
*/

package com.autosale.service.port.input;

import com.autosale.dto.CarRequestDTO;
import com.autosale.model.entity.car.Car;
import com.autosale.model.entity.car.Minivan;
import com.autosale.model.entity.car.Sedan;
import com.autosale.model.entity.car.Truck;
import com.autosale.repository.car.MinivanRepository;
import com.autosale.repository.car.SedanRepository;
import com.autosale.repository.car.TruckRepository;
import com.autosale.service.factory.CarFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

@Service
public class CarServiceImpl {

    private final Map<String, CarFactory> factories;
    private final SedanService sedanService;
    private final TruckService truckService;
    private final MinivanService minivanService;
//    private final SedanRepository sedanRepository;
//    private final TruckRepository truckRepository;
//    private final MinivanRepository minivanRepository;

    @Autowired
    public CarServiceImpl(List<CarFactory> factories, SedanService sedanService,
                          TruckService truckService,
                          MinivanService minivanService
//                          SedanRepository sedanRepository,
//                          TruckRepository truckRepository,
//                          MinivanRepository minivanRepository

    ) {
        this.factories = factories.stream().collect(toMap(CarFactory::getType, f -> f));

//        this.sedanRepository = sedanRepository;
//        this.truckRepository = truckRepository;
//        this.minivanRepository = minivanRepository;
        this.sedanService = sedanService;
        this.truckService = truckService;
        this.minivanService = minivanService;
    }

    public ResponseEntity<?> createCar(String type, CarRequestDTO requestDTO) {
        CarFactory factory = factories.get(type.toUpperCase());
        Car specificCar = factory.createCar(type, requestDTO);
        System.out.println(specificCar);
        try {
            switch (type.toLowerCase()) {
                case "sedan":
                    sedanService.saveCar((Sedan) specificCar);
                    System.out.println("Sedan saved");
                    break;
                case "truck":
                    truckService.saveCar((Truck) specificCar);
                    break;
                case "minivan":
                    minivanService.saveCar((Minivan) specificCar);
                    break;
                default:
                    return ResponseEntity.badRequest().body("Invalid car type");
            }

            return ResponseEntity.ok("Car saved successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}



package com.autosale.service.responseFactory;

import com.autosale.dto.*;
import com.autosale.mapper.MinivanMapper;
import com.autosale.model.entity.car.*;
import com.autosale.model.enums.CarTypeEnum;
import com.autosale.repository.factory.CarTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MinivanResponseFactory implements CarResponseFactory {

    private final CarTypeRepository repository;
    private final MinivanMapper minivanMapper;

    @Autowired
    public MinivanResponseFactory(CarTypeRepository repository, MinivanMapper minivanMapper) {
        this.repository = repository;
        this.minivanMapper = minivanMapper;
    }

    @Override
    public String getType() {
        return CarTypeEnum.MINIVAN.getCode();
    }

    @Override
    public CarResponseDto createCarDto(String type, Car car) {
        CarTypeEnum carTypeEnum = CarTypeEnum.MINIVAN;
        if (!carTypeEnum.getCode().equalsIgnoreCase(type)) {
            throw new IllegalArgumentException("SedanFactory can only create sedans");
        }

        CarType carType = repository.findByName(carTypeEnum.getCode())
                .orElseThrow(() -> new IllegalArgumentException("CarType not found"));

        MinivanResponseDto minivanDto = new MinivanResponseDto();
        minivanDto.setBrand(car.getBrand());
        minivanDto.setModel(car.getModel());
        minivanDto.setYear(car.getYear());
        minivanDto.setCarTypeName(carType);
        minivanDto.setPrice(car.getPrice());
        minivanDto.setSeatingCapacity(((Minivan)car).getSeatingCapacity());
        return minivanDto;
    }

    @Override
    public CarDto carToDto(String type, Car car) {
//        CarTypeEnum carTypeEnum = CarTypeEnum.MINIVAN;
//        if (!carTypeEnum.getCode().equalsIgnoreCase(type)) {
//            throw new IllegalArgumentException("MinivanFactory can only create minivans");
//        }
//        CarType carType = repository.findByName(carTypeEnum.getCode())
//                .orElseThrow(() -> new IllegalArgumentException("CarType not found"));
//        MinivanDto minivanDto = new MinivanDto();
        return minivanMapper.toDto((Minivan) car);
    }
}

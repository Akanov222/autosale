package com.autosale.service.port.input;

import com.autosale.dto.CarSearchCriteria;
import com.autosale.dto.CarUpdateDto;
import com.autosale.dto.MinivanUpdateDto;
import com.autosale.model.entity.car.Car;
import com.autosale.model.enums.CarTypeEnum;
import com.autosale.model.entity.car.Minivan;
import com.autosale.repository.factory.MinivanRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MinivanRepositoryService implements CarRepositoryService {

    private final MinivanRepository minivanRepository;

    public MinivanRepositoryService(MinivanRepository minivanRepository) {
        this.minivanRepository = minivanRepository;
    }

    @Override
    public String getType() {
        return CarTypeEnum.MINIVAN.getCode();
    }

    @Override
    public Optional<Car> getCarById(Long id) {
        return minivanRepository.findById(id).map(car -> car);
    }

    @Override
    public Page<Car> searchCars(CarSearchCriteria criteria, Pageable pageable) {
        return minivanRepository.searchMinivans(criteria, pageable).map(minivan -> minivan);
    }

    @Override
    public Page<Car> getAllCars(Pageable pageable) {
        return minivanRepository.findAll(pageable).map(minivan -> minivan);
    }

    @Override
    public Optional<Car> saveCar(Car car) {
        Minivan minivan = (Minivan) car;
        return Optional.of(minivanRepository.save(minivan));
    }

    @Override
    public Optional<Car> updateCar(Car car) {
        Minivan minivan = (Minivan) car;
        if(!minivanRepository.existsById(minivan.getId())) {
            return Optional.empty();
        }
        return Optional.of(minivanRepository.save(minivan));
    }

    @Override
    public Optional<Car> partialUpdateCar(Long id, CarUpdateDto updateDto) {
        Optional<Minivan> existingCarOpt = minivanRepository.findById(id);

        if (existingCarOpt.isEmpty()) {
            return Optional.empty();
        }

        Minivan existingCar = existingCarOpt.get();
        if (updateDto.getBrand() != null) {
            existingCar.setBrand(updateDto.getBrand());
        }
        if (updateDto.getModel() != null) {
            existingCar.setModel(updateDto.getModel());
        }
        if (updateDto.getYear() != null) {
            existingCar.setYear(updateDto.getYear());
        }
        if (updateDto.getPrice() != null) {
            existingCar.setPrice(updateDto.getPrice());
        }

        if (updateDto instanceof MinivanUpdateDto minivanUpdate) {
            if (minivanUpdate.getSeatingCapacity() != null) {
                existingCar.setSeatingCapacity(minivanUpdate.getSeatingCapacity());
            }
        }
        Minivan savedCar = minivanRepository.save(existingCar);
        return Optional.of(savedCar);
    }

    @Override
    public void deleteCarById(Long id) {
        minivanRepository.deleteById(id);
    }
}

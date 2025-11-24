package com.autosale.service.port.input;

import com.autosale.dto.CarSearchCriteria;
import com.autosale.dto.CarUpdateDto;
import com.autosale.dto.SedanUpdateDto;
import com.autosale.model.entity.car.Car;
import com.autosale.model.enums.CarTypeEnum;
import com.autosale.model.entity.car.Sedan;
import com.autosale.repository.factory.SedanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SedanRepositoryService implements CarRepositoryService<Sedan> {

    private final SedanRepository sedanRepository;

    @Autowired
    public SedanRepositoryService(SedanRepository sedanRepository) {
        this.sedanRepository = sedanRepository;
    }

    @Override
    public String getType() {
        return CarTypeEnum.SEDAN.getCode();
    }

    @Override
    public Optional<Car> getCarById(Long id) {
        return sedanRepository.findById(id).map(car -> car);
    }

    @Override
    public Page<Car> searchCars(CarSearchCriteria criteria, Pageable pageable) {
        return sedanRepository.searchSedans(criteria, pageable).map(sedan -> sedan);
    }

    @Override
    public Page<Car> getAllCars(Pageable pageable) {
        return sedanRepository.findAll(pageable).map(sedan -> sedan);
    }

    @Override
    public Optional<Car> saveCar(Sedan sedan) {
        return Optional.of(sedanRepository.save(sedan));
    }

    @Override
    public Optional<Car> updateCar(Sedan sedan) {
        if(!sedanRepository.existsById(sedan.getId())) {
            return Optional.empty();
        }
        return Optional.of(sedanRepository.save(sedan));
    }

    @Override
    public Optional<Car> partialUpdateCar(Long id, CarUpdateDto updateDto) {
        Optional<Sedan> existingCarOpt = sedanRepository.findById(id);

        if (existingCarOpt.isEmpty()) {
            return Optional.empty();
        }

        Sedan existingCar = existingCarOpt.get();
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

        if (updateDto instanceof SedanUpdateDto sedanUpdate) {
            if (sedanUpdate.getTrunkCapacity() != null) {
                existingCar.setTrunkCapacity(sedanUpdate.getTrunkCapacity());
            }
        }
        Sedan savedCar = sedanRepository.save(existingCar);
        return Optional.of(savedCar);
    }

    @Override
    public void deleteCarById(Long id) {
        sedanRepository.deleteById(id);
    }
}

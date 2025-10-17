package com.autosale.service.port.input;

import com.autosale.model.entity.car.CarTypeEnum;
import com.autosale.model.entity.car.Sedan;
import com.autosale.repository.car.SedanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SedanService implements CarService<Sedan> {

    private final SedanRepository sedanRepository;

    @Autowired
    public SedanService(SedanRepository sedanRepository) {
        this.sedanRepository = sedanRepository;
    }

    @Override
    public void saveCar(Sedan sedan) {
        sedanRepository.save(sedan);
    }

    @Override
    public String getType() {
        return CarTypeEnum.SEDAN.getCode();
    }
}

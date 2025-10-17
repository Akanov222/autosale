package com.autosale.service.port.input;

import com.autosale.model.entity.car.Car;
import com.autosale.model.entity.car.Sedan;
import com.autosale.repository.car.SedanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.autosale.model.entity.car.CarTypeEnum.SEDAN;

@Service
public class SedanService implements CarService<Sedan> {

    private final SedanRepository sedanRepository;

    @Autowired
    public SedanService(SedanRepository sedanRepository) {
        this.sedanRepository = sedanRepository;
    }

    @Override
    public void saveCar(Sedan sedan) {
        System.out.println("SedanService " + sedan.getModel());
        System.out.println("SedanService " + sedan.getBrand());
        System.out.println("SedanService " + sedan.getYear());
        System.out.println("SedanService " + sedan.getPrice());
        System.out.println("SedanService " + sedan.getCarType().getId());
        System.out.println("SedanService " + sedan.getCarType().getName());
        System.out.println("SedanService " + sedan.getTrunkCapacity());
        sedanRepository.save(sedan);
    }

    @Override
    public String getType() {
        return SEDAN.getCode().toString();
    }

    @Override
    public Long getTypeId() {
        return 1L;
    }
}

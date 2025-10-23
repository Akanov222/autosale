package com.autosale.config;

import com.autosale.model.entity.car.Car;
import com.autosale.repository.car.MinivanRepository;
import com.autosale.repository.car.SedanRepository;
import com.autosale.repository.car.TruckRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Map;

//@Configuration
public class RepositoryConfig {
/*    @Bean
    public Map<String, JpaRepository<? extends Car, ? extends Number>> carRepositories(
            SedanRepository sedanRepository,
            MinivanRepository minivanRepository,
            TruckRepository truckRepository) {

        return Map.of(
                "sedan", sedanRepository,
                "minivan", minivanRepository,
                "truck", truckRepository
        );
    }*/
}

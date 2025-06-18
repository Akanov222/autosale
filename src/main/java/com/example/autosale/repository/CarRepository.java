package com.example.autosale.repository;

import com.example.autosale.dao.Car;
import com.example.autosale.dao.CarType;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> findByType(CarType type);
}

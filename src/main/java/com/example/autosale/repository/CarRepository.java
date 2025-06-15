package com.example.autosale.repository;

import com.example.autosale.dao.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}

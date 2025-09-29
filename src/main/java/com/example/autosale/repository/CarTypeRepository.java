package com.example.autosale.repository;

import com.example.autosale.model.CarType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarTypeRepository extends JpaRepository<CarType, Long> {
    
    Optional<CarType> findByName(String name);
}

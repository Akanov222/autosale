package com.example.autosale.repository;

import com.example.autosale.dao.Truck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TruckRepository extends GenericCarRepository<Truck> {
}

package com.example.autosale.repository;

import com.example.autosale.model.Sedan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SedanRepository extends JpaRepository<Sedan, Long> {
}

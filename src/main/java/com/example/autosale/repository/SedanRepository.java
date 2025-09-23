package com.example.autosale.repository;

import com.example.autosale.dao.Sedan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SedanRepository extends GenericCarRepository<Sedan> {
}

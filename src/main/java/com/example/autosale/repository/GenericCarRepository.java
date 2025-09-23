package com.example.autosale.repository;

import com.example.autosale.dao.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface GenericCarRepository<T extends Car> extends JpaRepository<T, Long> {
}

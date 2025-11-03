package com.autosale.repository.factory;

import com.autosale.model.entity.car.Minivan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MinivanRepository extends JpaRepository<Minivan, Long> {
}

package com.autosale.repository.car;

import com.autosale.model.entity.car.Sedan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MinivanRepository extends JpaRepository<Sedan.Minivan, Long> {
}

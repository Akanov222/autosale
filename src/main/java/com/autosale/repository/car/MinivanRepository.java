package com.autosale.repository.car;

import com.autosale.model.Minivan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MinivanRepository extends JpaRepository<Minivan, Long> {
}

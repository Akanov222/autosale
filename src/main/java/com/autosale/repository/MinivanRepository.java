package com.autosale.repository;

import com.autosale.dao.Minivan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MinivanRepository extends JpaRepository<Minivan, Long> {
}



package com.autosale.repository.factory;

import com.autosale.dto.CarSearchCriteria;
import com.autosale.model.entity.car.Minivan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MinivanRepository extends JpaRepository<Minivan, Long> {
    Page<Minivan> findAll(Pageable pageable);

    @Query("SELECT car FROM Minivan car WHERE " +
            "(:#{#criteria.brand} IS NULL OR car.brand = :#{#criteria.brand}) AND " +
            "(:#{#criteria.model} IS NULL OR car.model = :#{#criteria.model}) AND " +
            "(:#{#criteria.minYear} IS NULL OR car.year >= :#{#criteria.minYear}) AND " +
            "(:#{#criteria.maxYear} IS NULL OR car.year <= :#{#criteria.maxYear}) AND " +
            "(:#{#criteria.minPrice} IS NULL OR car.price >= :#{#criteria.minPrice}) AND " +
            "(:#{#criteria.maxPrice} IS NULL OR car.price <= :#{#criteria.maxPrice})")
    Page<Minivan> searchMinivans(@Param("criteria") CarSearchCriteria criteria, Pageable pageable);
}

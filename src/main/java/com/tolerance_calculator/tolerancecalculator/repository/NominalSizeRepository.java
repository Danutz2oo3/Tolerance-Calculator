package com.tolerance_calculator.tolerancecalculator.repository;

import com.tolerance_calculator.tolerancecalculator.entity.NominalSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NominalSizeRepository extends JpaRepository<NominalSize, Long> {
    @Query(value = "SELECT s FROM NominalSize s " +
            "WHERE s.lowerBound > :modelDimension " +
            "AND s.upperBound  <= :modelDimension")
    Optional<NominalSize> findByModelDimension(@Param("modelDimension") Long modelDimension);
}

package com.de.guehring.tolerancecalculator.nominalSize;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NominalSizeRepository extends JpaRepository<NominalSizeEntity, Long> {
    @Query(value = "SELECT s FROM NominalSizeEntity s " +
            "WHERE s.lowerBound < :nominalDimension " +
            "AND s.upperBound  >= :nominalDimension")
    NominalSizeEntity findByNominalDimension(@Param("nominalDimension") Float nominalDimension);
    @Query(value = "SELECT s.id FROM NominalSizeEntity s " +
            "WHERE s.lowerBound < :nominalDimension " +
            "AND s.upperBound  >= :nominalDimension")
    Long findIdByNominalDimension(@Param("nominalDimension") Float nominalDimension);

}

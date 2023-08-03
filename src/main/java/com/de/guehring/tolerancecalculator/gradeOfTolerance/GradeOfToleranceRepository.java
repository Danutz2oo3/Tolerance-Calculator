package com.de.guehring.tolerancecalculator.gradeOfTolerance;

import com.de.guehring.tolerancecalculator.standardAllowance.StandardAllowanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GradeOfToleranceRepository extends JpaRepository<GradeOfToleranceEntity,Long> {
    @Query(value = "SELECT s FROM GradeOfToleranceEntity s " +
            "WHERE (:nominalDimension is null or s.max >= :nominalDimension " +
            "and s.min < :nominalDimension)")
    List<GradeOfToleranceEntity> findGradeOfToleranceEntitiesBy(@Param("nominalDimension") Float nominalDimension);
    @Query(value = "SELECT got " +
            "FROM GradeOfToleranceEntity got, ToleranceClassEntity tce, StandardAllowanceEntity sa " +
            "where (:nominalDimension is null or tce.max>= :nominalDimension " +
            "and tce.min < :nominalDimension) " +
            "and got.id = tce.gradeOfTolerance.id " +
            "and (:standardAllowanceId is null or tce.standardAllowance.id = :standardAllowanceId) " +
            "and tce.standardAllowance = sa")
    List<GradeOfToleranceEntity> findGradeOfToleranceEntitiesBy(@Param("nominalDimension") Float nominalDimension, @Param("standardAllowanceId") Long standardAllowanceId);
    @Query("SELECT got.id FROM GradeOfToleranceEntity got WHERE got.name = :name")
    Long findIdByName(String name);
}

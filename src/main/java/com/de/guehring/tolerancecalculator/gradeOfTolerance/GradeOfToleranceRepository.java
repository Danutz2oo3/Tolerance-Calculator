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
            "WHERE (:modelDimension is null or s.max >= :modelDimension " +
            "and s.min < :modelDimension)")
    List<GradeOfToleranceEntity> findGradeOfToleranceEntitiesBy(@Param("modelDimension") Long modelDimension);
    @Query(value = "SELECT got " +
            "FROM GradeOfToleranceEntity got, ToleranceClassEntity tce, StandardAllowanceEntity sa " +
            "where (:modelDimension is null or tce.max>= :modelDimension " +
            "and tce.min < :modelDimension) " +
            "and got.id = tce.gradeOfTolerance.id " +
            "and (:standardAllowanceId is null or tce.standardAllowance.id = :standardAllowanceId) " +
            "and tce.standardAllowance = sa")
    List<GradeOfToleranceEntity> findGradeOfToleranceEntitiesBy(@Param("modelDimension") Long modelDimension, @Param("standardAllowanceId") Long standardAllowanceId);





}

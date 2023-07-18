package com.de.guehring.tolerancecalculator.gradeOfTolerance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeOfToleranceRepository extends JpaRepository<GradeOfToleranceEntity,Long> {
    @Query(value = "SELECT s FROM GradeOfToleranceEntity s " +
            "WHERE (:modelDimension is null or s.max >= :modelDimension " +
            "and s.min < :modelDimension)")
    List<GradeOfToleranceEntity> findGradeOfToleranceEntitiesBy(@Param("modelDimension") Long modelDimension);

}

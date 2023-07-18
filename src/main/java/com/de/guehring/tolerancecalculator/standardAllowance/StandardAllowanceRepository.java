package com.de.guehring.tolerancecalculator.standardAllowance;

import com.de.guehring.tolerancecalculator.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.lang.annotation.Native;
import java.util.List;
import java.util.Optional;

@Repository
public interface StandardAllowanceRepository extends JpaRepository<StandardAllowanceEntity,Long> {
    @Query(value = "SELECT s FROM StandardAllowanceEntity s " +
            "WHERE (:modelDimension is null or s.max >= :modelDimension " +
            "and s.min < :modelDimension) " +
            "and (:type is null or s.type = :type)")
    List<StandardAllowanceEntity> findStandardAllowanceEntitiesBy(@Param("modelDimension") Long modelDimension, @Param("type") Type type);

    @Query(value = "SELECT sa " +
            "FROM GradeOfToleranceEntity got, ToleranceClassEntity tce, StandardAllowanceEntity sa " +
            "where (:modelDimension is null or tce.max >= :modelDimension " +
            "and tce.min < :modelDimension) " +
            "and sa.id = tce.standardAllowance.id " +
            "and (:gradeOfToleranceId is null or tce.gradeOfTolerance.id = :gradeOfToleranceId) " +
            "and tce.gradeOfTolerance = got " +
            "and (:type is null or sa.type = :type)")
    List<StandardAllowanceEntity> findStandardAllowanceEntitiesBy(@Param("modelDimension") Long modelDimension, @Param("type") Type type, @Param("gradeOfToleranceId") Long gradeOfToleranceId);
}

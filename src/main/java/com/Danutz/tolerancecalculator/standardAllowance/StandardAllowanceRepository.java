package com.Danutz.tolerancecalculator.standardAllowance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StandardAllowanceRepository extends JpaRepository<StandardAllowanceEntity,Long> {
    @Query(value = "SELECT s FROM StandardAllowanceEntity s " +
            "WHERE (:nominalDimension is null or s.max >= :nominalDimension " +
            "and s.min < :nominalDimension) " +
            "and (:type is null or s.type = :type)")
    List<StandardAllowanceEntity> findStandardAllowanceEntitiesBy(@Param("nominalDimension") Float nominalDimension, @Param("type") Type type);

    @Query(value = "SELECT sa " +
            "FROM GradeOfToleranceEntity got, ToleranceClassEntity tce, StandardAllowanceEntity sa " +
            "where (:nominalDimension is null or tce.max >= :nominalDimension " +
            "and tce.min < :nominalDimension) " +
            "and sa.id = tce.standardAllowance.id " +
            "and (:gradeOfToleranceId is null or tce.gradeOfTolerance.id = :gradeOfToleranceId) " +
            "and tce.gradeOfTolerance = got " +
            "and (:type is null or sa.type = :type)")
    List<StandardAllowanceEntity> findStandardAllowanceEntitiesBy(@Param("nominalDimension") Float nominalDimension, @Param("type") Type type, @Param("gradeOfToleranceId") Long gradeOfToleranceId);

    @Query("SELECT sa.id FROM StandardAllowanceEntity sa WHERE sa.name = :name AND sa.type = :type")
    Long findIdByNameAndType(String name, Type type);
}

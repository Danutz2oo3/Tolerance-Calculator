package com.de.guehring.tolerancecalculator.toleranceClass;

import com.de.guehring.tolerancecalculator.gradeOfTolerance.GradeOfToleranceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ToleranceClassRepository extends JpaRepository<ToleranceClassEntity,ToleranceClassId> {
    @Query(value = "select s from ToleranceClassEntity s " +
            "where (s.min < :nominalDimension and s.max >= :nominalDimension) " +
            "and s.gradeOfTolerance.id = :gradeOfToleranceId " +
            "and s.standardAllowance.id = :standardAllowanceId")
    ToleranceClassEntity findToleranceClassEntityBy(@Param("nominalDimension") Float nominalDimension,
                                                    @Param("gradeOfToleranceId") Long gradeOfToleranceId,
                                                    @Param("standardAllowanceId") Long standardAllowanceId);

}

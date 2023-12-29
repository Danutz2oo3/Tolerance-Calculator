package com.Danutz.tolerancecalculator.tolerance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ToleranceRepository extends JpaRepository<ToleranceEntity, Long> {
    @Query(value = "select s from ToleranceEntity s " +
            "where (:nominalSizeId = s.nominalSize.id " +
            "and :standardAllowanceId = s.toleranceClass.standardAllowance.id " +
            "and :gradeOfToleranceId = s.toleranceClass.gradeOfTolerance.id)")
    ToleranceEntity findToleranceEntityBy(@Param("nominalSizeId") Long nominalSizeId,
                                          @Param("standardAllowanceId") Long standardAllowanceId,
                                          @Param("gradeOfToleranceId") Long gradeOfToleranceId);
}

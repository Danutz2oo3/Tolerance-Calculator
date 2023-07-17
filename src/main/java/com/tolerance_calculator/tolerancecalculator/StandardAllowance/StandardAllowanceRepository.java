package com.tolerance_calculator.tolerancecalculator.StandardAllowance;

import com.tolerance_calculator.tolerancecalculator.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StandardAllowanceRepository extends JpaRepository<StandardAllowanceEntity,Long> {
    @Query(value = "SELECT s FROM StandardAllowanceEntity s " +
            "WHERE (:modelDimension is null or s.max >= :modelDimension " +
            "and s.min < :modelDimension) " +
            "and (:type is null or s.type = :type)")
    List<StandardAllowanceEntity> findStandardAllowanceEnt(@Param("modelDimension") Long modelDimension, @Param("type") Type type);

}

package com.de.guehring.tolerancecalculator.toleranceClass;

import com.de.guehring.tolerancecalculator.gradeOfTolerance.GradeOfToleranceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ToleranceClassRepository extends JpaRepository<ToleranceClassEntity,ToleranceClassId> {

}

package com.tolerance_calculator.tolerancecalculator.GradeOfTolerance;

import com.tolerance_calculator.tolerancecalculator.StandardAllowance.StandardAllowanceEntity;
import com.tolerance_calculator.tolerancecalculator.entity.Type;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "grade_of_tolerance_entity")
public class GradeOfToleranceEntity extends StandardAllowanceEntity {
    private List<Short> gradeOfToleranceList;
}
package com.de.guehring.tolerancecalculator.toleranceClass;

import com.de.guehring.tolerancecalculator.gradeOfTolerance.GradeOfToleranceEntity;
import com.de.guehring.tolerancecalculator.standardAllowance.StandardAllowanceEntity;
import jakarta.persistence.*;



@Entity
@IdClass(ToleranceClassId.class)
public class ToleranceClassEntity {
    @Id
    @ManyToOne
    @JoinColumn(name = "SA_id")
    private StandardAllowanceEntity standardAllowance;
    @Id
    @ManyToOne
    @JoinColumn(name = "GoT_id")
    private GradeOfToleranceEntity gradeOfTolerance;
    private Long max;
    private Long min;
}
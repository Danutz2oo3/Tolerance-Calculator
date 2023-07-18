package com.de.guehring.tolerancecalculator.toleranceClass;

import com.de.guehring.tolerancecalculator.gradeOfTolerance.GradeOfToleranceEntity;
import com.de.guehring.tolerancecalculator.standardAllowance.StandardAllowanceEntity;

import java.io.Serializable;
import java.util.Objects;

public class ToleranceClassId implements Serializable {
    private GradeOfToleranceEntity gradeOfTolerance;
    private StandardAllowanceEntity standardAllowance;

    public ToleranceClassId(GradeOfToleranceEntity gradeOfTolerance, StandardAllowanceEntity standardAllowance) {
        this.gradeOfTolerance = gradeOfTolerance;
        this.standardAllowance = standardAllowance;
    }
    public ToleranceClassId() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToleranceClassId that = (ToleranceClassId) o;
        return Objects.equals(gradeOfTolerance, that.gradeOfTolerance) && Objects.equals(standardAllowance, that.standardAllowance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gradeOfTolerance, standardAllowance);
    }
}

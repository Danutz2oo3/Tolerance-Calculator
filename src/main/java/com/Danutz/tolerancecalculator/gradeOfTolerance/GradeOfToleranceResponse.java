package com.Danutz.tolerancecalculator.gradeOfTolerance;

public record GradeOfToleranceResponse(
        Long id,
        Float min,
        Float max,
        String name
) {
}

package com.de.guehring.tolerancecalculator.gradeOfTolerance;

public record GradeOfToleranceResponse(
        Long id,
        Float min,
        Float max,
        String name
) {
}

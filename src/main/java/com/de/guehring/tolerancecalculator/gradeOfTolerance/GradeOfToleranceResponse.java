package com.de.guehring.tolerancecalculator.gradeOfTolerance;

public record GradeOfToleranceResponse(
        Long id,
        Long min,
        Long max,
        String name
) {
}

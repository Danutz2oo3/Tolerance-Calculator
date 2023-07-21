package com.de.guehring.tolerancecalculator.model;

public record BoreHole(
        Long nominalDimension,
        Float upperLimitDeviation,
        Float lowerLimitDeviation
) {
}

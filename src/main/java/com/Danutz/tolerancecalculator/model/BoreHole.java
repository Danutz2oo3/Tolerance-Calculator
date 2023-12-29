package com.Danutz.tolerancecalculator.model;

import java.math.BigDecimal;

public record BoreHole(
        BigDecimal nominalDimension,
        BigDecimal upperLimitDeviation,
        BigDecimal lowerLimitDeviation
) {
}

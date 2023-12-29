package com.Danutz.tolerancecalculator.standardAllowance;

public record StandardAllowanceResponse(
         Long id,
         String name,
         Type type,
         Float max,
         Float min
) {
}

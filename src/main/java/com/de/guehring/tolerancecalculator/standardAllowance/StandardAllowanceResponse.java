package com.de.guehring.tolerancecalculator.standardAllowance;

import com.de.guehring.tolerancecalculator.standardAllowance.Type;

public record StandardAllowanceResponse(
         Long id,
         String name,
         Type type,
         Long max,
         Long min
) {
}

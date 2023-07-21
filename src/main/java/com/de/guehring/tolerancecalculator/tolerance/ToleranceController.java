package com.de.guehring.tolerancecalculator.tolerance;

import com.de.guehring.tolerancecalculator.gradeOfTolerance.GradeOfToleranceRepository;
import com.de.guehring.tolerancecalculator.model.BoreHole;
import com.de.guehring.tolerancecalculator.model.Test;
import com.de.guehring.tolerancecalculator.nominalSize.NominalSizeEntity;
import com.de.guehring.tolerancecalculator.nominalSize.NominalSizeRepository;
import com.de.guehring.tolerancecalculator.standardAllowance.StandardAllowanceRepository;
import com.de.guehring.tolerancecalculator.standardAllowance.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ToleranceController {
    private final ToleranceService toleranceService;
    private final NominalSizeRepository nominalSizeRepository;
    private final StandardAllowanceRepository standardAllowanceRepository;
    private final GradeOfToleranceRepository gradeOfToleranceRepository;

    @Autowired
    public ToleranceController(ToleranceService toleranceService,
                               NominalSizeRepository nominalSizeRepository,
                               StandardAllowanceRepository standardAllowanceRepository,
                               GradeOfToleranceRepository gradeOfToleranceRepository) {
        this.toleranceService = toleranceService;
        this.nominalSizeRepository = nominalSizeRepository;
        this.standardAllowanceRepository = standardAllowanceRepository;
        this.gradeOfToleranceRepository = gradeOfToleranceRepository;
    }
    @GetMapping("api/v1/tolerance")
    public BoreHole getTolerance(@RequestParam(value = "nominalDimension", required = true) Long nominalDimension,
                                        @RequestParam(value = "standardAllowance", required = true) String standardAllowance,
                                        @RequestParam(value = "gradeOfTolerance", required = true) String gradeOfTolerance,
                                        @RequestParam(value = "type", required = true)Type type){

        Long nominalSizeId = nominalSizeRepository.findIdByNominalDimension(nominalDimension);
        Long standardAllowanceId = standardAllowanceRepository.findIdByNameAndType(standardAllowance,type);
        Long gradeOfToleranceId = gradeOfToleranceRepository.findIdByName(gradeOfTolerance);
        Float upperLimitDeviation =toleranceService.getTolerance(nominalSizeId,standardAllowanceId,gradeOfToleranceId).getUpperLimitDeviation();
        Float lowerLimitDeviation =toleranceService.getTolerance(nominalSizeId,standardAllowanceId,gradeOfToleranceId).getLowerLimitDeviation();
        return new BoreHole(nominalDimension,nominalDimension+upperLimitDeviation,nominalDimension+lowerLimitDeviation);
    }
}

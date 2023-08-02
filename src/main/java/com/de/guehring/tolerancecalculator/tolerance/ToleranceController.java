package com.de.guehring.tolerancecalculator.tolerance;

import com.de.guehring.tolerancecalculator.gradeOfTolerance.GradeOfToleranceRepository;
import com.de.guehring.tolerancecalculator.gradeOfTolerance.GradeOfToleranceService;
import com.de.guehring.tolerancecalculator.model.BoreHole;
import com.de.guehring.tolerancecalculator.nominalSize.NominalSizeRepository;
import com.de.guehring.tolerancecalculator.nominalSize.NominalSizeService;
import com.de.guehring.tolerancecalculator.standardAllowance.StandardAllowanceRepository;
import com.de.guehring.tolerancecalculator.standardAllowance.StandardAllowanceService;
import com.de.guehring.tolerancecalculator.standardAllowance.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class
ToleranceController {
    private final ToleranceService toleranceService;
    private final NominalSizeService nominalSizeService;
    private final StandardAllowanceService standardAllowanceService;
    private final GradeOfToleranceService gradeOfToleranceService;

    @Autowired
    public ToleranceController(ToleranceService toleranceService,
                               NominalSizeService nominalSizeService,
                               StandardAllowanceService standardAllowanceService,
                               GradeOfToleranceService gradeOfToleranceService) {
        this.toleranceService = toleranceService;
        this.nominalSizeService = nominalSizeService;
        this.standardAllowanceService = standardAllowanceService;
        this.gradeOfToleranceService = gradeOfToleranceService;
    }

    @GetMapping("api/v1/tolerance")
    public BoreHole getTolerance(@RequestParam(value = "nominalDimension", required = true) Long nominalDimension,
                                 @RequestParam(value = "standardAllowance", required = true) String standardAllowance,
                                 @RequestParam(value = "gradeOfTolerance", required = true) String gradeOfTolerance,
                                 @RequestParam(value = "type", required = true) Type type) {

        Long nominalSizeId = nominalSizeService.getNominalDimensionId(nominalDimension);
        Long standardAllowanceId = standardAllowanceService.getStandardAllowanceIdByName(standardAllowance, type);
        Long gradeOfToleranceId = gradeOfToleranceService.getIdByName(gradeOfTolerance);
        if(nominalSizeId == null || standardAllowanceId == null || gradeOfToleranceId == null){
            throw new IllegalStateException("tolerance does not exist for this input");
        }
        else {
            ToleranceEntity toleranceEntity = toleranceService.getTolerance(nominalSizeId, standardAllowanceId, gradeOfToleranceId);
            if (toleranceEntity != null) {
                Float upperLimitDeviation = toleranceEntity.getUpperLimitDeviation();
                Float lowerLimitDeviation = toleranceEntity.getLowerLimitDeviation();
                return new BoreHole(nominalDimension,
                        nominalDimension + upperLimitDeviation,
                        nominalDimension + lowerLimitDeviation);
            } else {
                throw new IllegalStateException("tolerance does not exist for this input");
            }
        }
    }
}

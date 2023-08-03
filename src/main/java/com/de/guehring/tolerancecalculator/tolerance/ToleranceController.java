package com.de.guehring.tolerancecalculator.tolerance;

import com.de.guehring.tolerancecalculator.errorResponse.CustomException;
import com.de.guehring.tolerancecalculator.gradeOfTolerance.GradeOfToleranceRepository;
import com.de.guehring.tolerancecalculator.gradeOfTolerance.GradeOfToleranceService;
import com.de.guehring.tolerancecalculator.model.BoreHole;
import com.de.guehring.tolerancecalculator.nominalSize.NominalSizeRepository;
import com.de.guehring.tolerancecalculator.nominalSize.NominalSizeService;
import com.de.guehring.tolerancecalculator.standardAllowance.StandardAllowanceRepository;
import com.de.guehring.tolerancecalculator.standardAllowance.StandardAllowanceService;
import com.de.guehring.tolerancecalculator.standardAllowance.Type;
import com.de.guehring.tolerancecalculator.toleranceClass.ToleranceClassEntity;
import com.de.guehring.tolerancecalculator.toleranceClass.ToleranceClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

@RestController
public class
ToleranceController {
    private final ToleranceService toleranceService;
    private final NominalSizeService nominalSizeService;
    private final StandardAllowanceService standardAllowanceService;
    private final GradeOfToleranceService gradeOfToleranceService;
    private final ToleranceClassService toleranceClassService;

    @Autowired
    public ToleranceController(ToleranceService toleranceService,
                               NominalSizeService nominalSizeService,
                               StandardAllowanceService standardAllowanceService,
                               GradeOfToleranceService gradeOfToleranceService,
                               ToleranceClassService toleranceClassService) {
        this.toleranceService = toleranceService;
        this.nominalSizeService = nominalSizeService;
        this.standardAllowanceService = standardAllowanceService;
        this.gradeOfToleranceService = gradeOfToleranceService;
        this.toleranceClassService = toleranceClassService;
    }

    @GetMapping("api/v1/tolerance")
    public BoreHole getTolerance(@RequestParam(value = "nominalDimension", required = true) Float nominalDimension,
                                 @RequestParam(value = "standardAllowance", required = true) String standardAllowance,
                                 @RequestParam(value = "gradeOfTolerance", required = true) String gradeOfTolerance,
                                 @RequestParam(value = "type", required = true) Type type) {
        BigDecimal nominalDimensionBG = new BigDecimal(nominalDimension);
        Long nominalSizeId = nominalSizeService.getNominalDimensionId(nominalDimension);
        Long standardAllowanceId = standardAllowanceService.getStandardAllowanceIdByName(standardAllowance, type);
        Long gradeOfToleranceId = gradeOfToleranceService.getIdByName(gradeOfTolerance);
        ToleranceClassEntity checkToleranceClass = toleranceClassService.getToleranceClass(nominalDimension,gradeOfToleranceId,standardAllowanceId);
        if(nominalSizeId == null || standardAllowanceId == null || gradeOfToleranceId == null){
            throw new CustomException(400, "cannot calculate values for this input");
        }
        else {
            if(checkToleranceClass == null){
                throw new CustomException(400, "cannot calculate values for this input");
            }
            else{
                ToleranceEntity toleranceEntity = toleranceService.getTolerance(nominalSizeId, standardAllowanceId, gradeOfToleranceId);
                if (toleranceEntity != null) {
                    BigDecimal upperLimitDeviation = new BigDecimal(toleranceEntity.getUpperLimitDeviation());
                    BigDecimal lowerLimitDeviation = new BigDecimal(toleranceEntity.getLowerLimitDeviation());
                    return new BoreHole(nominalDimensionBG.setScale(4, RoundingMode.HALF_UP),
                            nominalDimensionBG.add(upperLimitDeviation).setScale(4,RoundingMode.HALF_UP) ,
                            nominalDimensionBG.add(lowerLimitDeviation).setScale(4,RoundingMode.HALF_UP));
                } else {
                    throw new CustomException(400, "cannot calculate values for this input");
                }
            }
        }
    }
}


package com.Danutz.tolerancecalculator.gradeOfTolerance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = {"api/v1/gradeOfTolerance"})
public class GradeOfToleranceController {
    private final GradeOfToleranceService gradeOfToleranceService;

    @Autowired
    public GradeOfToleranceController(GradeOfToleranceService gradeOfToleranceService) {
        this.gradeOfToleranceService = gradeOfToleranceService;
    }

    @GetMapping
    public List<GradeOfToleranceResponse> getGradeOfTolerance(
            @RequestParam(value = "modelSize", required = false) Optional<Float> modelSizeOptional,
            @RequestParam(value = "standardAllowanceId", required = false) Optional<Long> standardAllowanceIdOptional
    ) {
        List<GradeOfToleranceResponse> gradeOfToleranceResponses;
        Float modelSize = modelSizeOptional.orElse(null);
        Long standardAllowanceId = standardAllowanceIdOptional.orElse(null);

        if(standardAllowanceId == null) {
            List<GradeOfToleranceEntity> gradeOfToleranceEntities = gradeOfToleranceService.getGradeOfTolerance(modelSize);
            gradeOfToleranceResponses = mapToDTOs(gradeOfToleranceEntities);
        } else {
            List<GradeOfToleranceEntity> gradeOfToleranceEntities = gradeOfToleranceService.getGradeOfToleranceEntitiesByStandardAllowance(modelSize, standardAllowanceId);
            gradeOfToleranceResponses = mapToDTOs(gradeOfToleranceEntities);
        }

        return gradeOfToleranceResponses;
    }

    private List<GradeOfToleranceResponse> mapToDTOs(List<GradeOfToleranceEntity> gradeOfToleranceEntities) {
        return gradeOfToleranceEntities.stream()
                .map(entity -> new GradeOfToleranceResponse(
                        entity.getId(),
                        entity.getMin(),
                        entity.getMax(),
                        entity.getName()
                ))
                .collect(Collectors.toList());
    }
}

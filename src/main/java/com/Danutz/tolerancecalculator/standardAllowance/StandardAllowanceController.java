package com.Danutz.tolerancecalculator.standardAllowance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/v1/standardAllowance")
public class StandardAllowanceController {
    private final StandardAllowanceService standardAllowanceService;

    @Autowired
    public StandardAllowanceController(StandardAllowanceService standardAllowanceService) {
        this.standardAllowanceService = standardAllowanceService;
    }
    @GetMapping
    public List<StandardAllowanceResponse> getStandardAllowance(@RequestParam(value = "modelSize", required = false) Optional<Float> modelSizeOptional,
                                                             @RequestParam(value = "type", required = false) Optional<Type> typeOptional,
                                                             @RequestParam(value = "gradeOfTolerance", required = false)Optional<Long> gOTOptional) {
        List<StandardAllowanceResponse> standardAllowanceResponses;
        Float modelSize = modelSizeOptional.orElse(null);
        Type type = typeOptional.orElse(null);
        Long gOT = gOTOptional.orElse(null);

        if(gOT == null){
            List<StandardAllowanceEntity> standardAllowanceEntities = standardAllowanceService.getStandardAllowance(modelSize,type);
            standardAllowanceResponses = mapToDTOs(standardAllowanceEntities);
        }
        else{
            List<StandardAllowanceEntity> standardAllowanceEntities = standardAllowanceService.getStandardAllowanceByGradeOfTolerance(modelSize,type,gOT);
            standardAllowanceResponses = mapToDTOs(standardAllowanceEntities);
        }
        return standardAllowanceResponses;
    }
    private List<StandardAllowanceResponse> mapToDTOs(List<StandardAllowanceEntity> standardAllowanceEntities) {
        return standardAllowanceEntities.stream()
                .map(entity -> new StandardAllowanceResponse(
                        entity.getId(),
                        entity.getName(),
                        entity.getType(),
                        entity.getMax(),
                        entity.getMin()
                ))
                .collect(Collectors.toList());
    }

}


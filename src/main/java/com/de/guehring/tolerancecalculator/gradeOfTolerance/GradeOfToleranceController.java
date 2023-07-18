package com.de.guehring.tolerancecalculator.gradeOfTolerance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = {"api/v1/gradeOfTolerance"})
public class GradeOfToleranceController {
    private final GradeOfToleranceService gradeOfToleranceService;
    @Autowired
    public GradeOfToleranceController(GradeOfToleranceService gradeOfToleranceService) {
        this.gradeOfToleranceService = gradeOfToleranceService;
    }
    @GetMapping
    public ResponseEntity<List<String>> getGradeOfTolerance(@RequestParam(value = "modelSize", required = false) Long modelSize) {
        List<GradeOfToleranceEntity> gradeOfToleranceEntities;
        List<String> gradeOfToleranceNames = new ArrayList<>();

        gradeOfToleranceEntities = gradeOfToleranceService.getGradeOfTolerance(modelSize);

        for ( GradeOfToleranceEntity gradeOfTolerance : gradeOfToleranceEntities) {
            gradeOfToleranceNames.add(gradeOfTolerance.getName());
        }

        return new ResponseEntity<>(gradeOfToleranceNames, HttpStatus.OK);
    }
}

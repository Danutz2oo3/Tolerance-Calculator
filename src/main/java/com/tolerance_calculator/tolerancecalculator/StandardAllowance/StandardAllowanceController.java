package com.tolerance_calculator.tolerancecalculator.StandardAllowance;

import com.tolerance_calculator.tolerancecalculator.entity.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/standardAllowance")
public class StandardAllowanceController {
    private final StandardAllowanceService standardAllowanceService;

    @Autowired
    public StandardAllowanceController(StandardAllowanceService standardAllowanceService) {
        this.standardAllowanceService = standardAllowanceService;
    }

    @GetMapping
    public ResponseEntity<List<String>> getStandardAllowance(@RequestParam(value = "modelSize", required = false) Long modelSize,
                                                             @RequestParam(value = "type", required = false) Type type) {
        List<StandardAllowanceEntity> standardAllowanceEntities;
        List<String> standardAllowanceNames = new ArrayList<>();

        standardAllowanceEntities = standardAllowanceService.getStandardAllowance(modelSize,type);

        for (StandardAllowanceEntity allowance : standardAllowanceEntities) {
            standardAllowanceNames.add(allowance.getName());
        }

        return new ResponseEntity<>(standardAllowanceNames, HttpStatus.OK);
    }

}


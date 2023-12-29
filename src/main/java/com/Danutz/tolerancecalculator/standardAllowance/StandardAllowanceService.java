package com.Danutz.tolerancecalculator.standardAllowance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StandardAllowanceService {
    private final StandardAllowanceRepository standardAllowanceRepository;

    @Autowired
    public StandardAllowanceService(StandardAllowanceRepository standardAllowanceRepository) {
        this.standardAllowanceRepository = standardAllowanceRepository;
    }

    public List<StandardAllowanceEntity> getStandardAllowance(Float modelSize, Type type) {
        return standardAllowanceRepository.findStandardAllowanceEntitiesBy(modelSize, type);
    }

    public List<StandardAllowanceEntity> getAllStandardAllowances() {
        return standardAllowanceRepository.findAll();
    }
    public List<StandardAllowanceEntity> getStandardAllowanceByGradeOfTolerance(Float modelSize, Type type, Long gradeOfToleranceId){
        return standardAllowanceRepository.findStandardAllowanceEntitiesBy(modelSize, type, gradeOfToleranceId);
    }
    public Long getStandardAllowanceIdByName(String name, Type type) {
        return standardAllowanceRepository.findIdByNameAndType(name,type);
    }

}
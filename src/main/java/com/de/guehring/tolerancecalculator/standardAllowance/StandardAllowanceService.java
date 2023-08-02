package com.de.guehring.tolerancecalculator.standardAllowance;

import com.de.guehring.tolerancecalculator.standardAllowance.Type;
import com.de.guehring.tolerancecalculator.gradeOfTolerance.GradeOfToleranceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StandardAllowanceService {
    private final StandardAllowanceRepository standardAllowanceRepository;

    @Autowired
    public StandardAllowanceService(StandardAllowanceRepository standardAllowanceRepository) {
        this.standardAllowanceRepository = standardAllowanceRepository;
    }

    public List<StandardAllowanceEntity> getStandardAllowance(Long modelSize, Type type) {
        return standardAllowanceRepository.findStandardAllowanceEntitiesBy(modelSize, type);
    }

    public List<StandardAllowanceEntity> getAllStandardAllowances() {
        return standardAllowanceRepository.findAll();
    }
    public List<StandardAllowanceEntity> getStandardAllowanceByGradeOfTolerance(Long modelSize, Type type, Long gradeOfToleranceId){
        return standardAllowanceRepository.findStandardAllowanceEntitiesBy(modelSize, type, gradeOfToleranceId);
    }
    public Long getStandardAllowanceIdByName(String name, Type type) {
        return standardAllowanceRepository.findIdByNameAndType(name,type);
    }

}
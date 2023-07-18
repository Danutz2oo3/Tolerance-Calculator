package com.de.guehring.tolerancecalculator.gradeOfTolerance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeOfToleranceService {
    private final GradeOfToleranceRepository gradeOfToleranceRepository;
    @Autowired
    public GradeOfToleranceService(GradeOfToleranceRepository gradeOfToleranceRepository) {
        this.gradeOfToleranceRepository = gradeOfToleranceRepository;
    }
    public List<GradeOfToleranceEntity> getGradeOfTolerance(Long modelDimension){
        return gradeOfToleranceRepository.findGradeOfToleranceEntitiesBy(modelDimension);
    }
    public List<GradeOfToleranceEntity> getAllGradeOfTolerance(){
        return gradeOfToleranceRepository.findAll();
    }
}
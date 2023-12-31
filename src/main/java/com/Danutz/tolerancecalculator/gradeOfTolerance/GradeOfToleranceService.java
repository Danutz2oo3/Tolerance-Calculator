package com.Danutz.tolerancecalculator.gradeOfTolerance;

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
    public List<GradeOfToleranceEntity> getGradeOfTolerance(Float nominalDimension){
        return gradeOfToleranceRepository.findGradeOfToleranceEntitiesBy(nominalDimension);
    }
    public List<GradeOfToleranceEntity> getAllGradeOfTolerance(){
        return gradeOfToleranceRepository.findAll();
    }
    public List<GradeOfToleranceEntity> getGradeOfToleranceEntitiesByStandardAllowance(Float nominalDimension, Long standardAllowanceId){
        return gradeOfToleranceRepository.findGradeOfToleranceEntitiesBy(nominalDimension,standardAllowanceId);
    }
    public Long getIdByName(String name) {
        return gradeOfToleranceRepository.findIdByName(name);
    }
}

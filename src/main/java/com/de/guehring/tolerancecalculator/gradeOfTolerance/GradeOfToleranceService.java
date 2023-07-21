package com.de.guehring.tolerancecalculator.gradeOfTolerance;

import com.de.guehring.tolerancecalculator.standardAllowance.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GradeOfToleranceService {
    private final GradeOfToleranceRepository gradeOfToleranceRepository;
    @Autowired
    public GradeOfToleranceService(GradeOfToleranceRepository gradeOfToleranceRepository) {
        this.gradeOfToleranceRepository = gradeOfToleranceRepository;
    }
    public List<GradeOfToleranceEntity> getGradeOfTolerance(Long nominalDimension){
        return gradeOfToleranceRepository.findGradeOfToleranceEntitiesBy(nominalDimension);
    }
    public List<GradeOfToleranceEntity> getAllGradeOfTolerance(){
        return gradeOfToleranceRepository.findAll();
    }
    public List<GradeOfToleranceEntity> getGradeOfToleranceEntitiesByStandardAllowance(Long nominalDimension, Long standardAllowanceId){
        return gradeOfToleranceRepository.findGradeOfToleranceEntitiesBy(nominalDimension,standardAllowanceId);
    }
    public Long getIdByName(String name) {
        return gradeOfToleranceRepository.findIdByName(name);
    }
}

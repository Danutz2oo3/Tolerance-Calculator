package com.Danutz.tolerancecalculator.toleranceClass;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ToleranceClassService {
    private final ToleranceClassRepository toleranceClassRepository;
    @Autowired
    public ToleranceClassService(ToleranceClassRepository toleranceClassRepository) {
        this.toleranceClassRepository = toleranceClassRepository;
    }
    public ToleranceClassEntity getToleranceClass(Float nominalDimension, Long gradeOfToleranceId, Long standardAllowanceId){
        return toleranceClassRepository.findToleranceClassEntityBy(nominalDimension, gradeOfToleranceId, standardAllowanceId);
    }
}

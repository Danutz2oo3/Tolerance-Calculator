package com.Danutz.tolerancecalculator.tolerance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ToleranceService {
    private final ToleranceRepository toleranceRepository;
    @Autowired
    public ToleranceService(ToleranceRepository toleranceRepository) {
        this.toleranceRepository = toleranceRepository;
    }
    public ToleranceEntity getTolerance(Long nominalSizeId, Long standardAllowanceId, Long gradeOfToleranceId){
        return toleranceRepository.findToleranceEntityBy(nominalSizeId,standardAllowanceId,gradeOfToleranceId);
    }

}

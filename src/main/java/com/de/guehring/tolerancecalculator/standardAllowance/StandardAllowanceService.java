package com.de.guehring.tolerancecalculator.standardAllowance;

import com.de.guehring.tolerancecalculator.entity.Type;
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

    public List<StandardAllowanceEntity> getStandardAllowance(Long modelSize, Type type) {
        return standardAllowanceRepository.findStandardAllowanceEnt(modelSize, type);
    }

    public List<StandardAllowanceEntity> getAllStandardAllowances() {
        return standardAllowanceRepository.findAll();
    }
}
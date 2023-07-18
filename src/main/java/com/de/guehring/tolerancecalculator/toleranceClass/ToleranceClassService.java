package com.de.guehring.tolerancecalculator.toleranceClass;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToleranceClassService {
    private final ToleranceClassRepository toleranceClassRepository;
    @Autowired
    public ToleranceClassService(ToleranceClassRepository toleranceClassRepository) {
        this.toleranceClassRepository = toleranceClassRepository;
    }

}

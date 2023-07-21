package com.de.guehring.tolerancecalculator.nominalSize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NominalSizeService {
    private final NominalSizeRepository nominalSizeRepository;
    @Autowired
    public NominalSizeService(NominalSizeRepository nominalSizeRepository) {
        this.nominalSizeRepository = nominalSizeRepository;
    }

    public NominalSizeEntity getNominalDimension(Long nominalDimension){
        return nominalSizeRepository.findBynominalDimension(nominalDimension);
    }
    public Long getNominalDimensionId(Long nominalDimension){
        return nominalSizeRepository.findIdByNominalDimension(nominalDimension);
    }
}

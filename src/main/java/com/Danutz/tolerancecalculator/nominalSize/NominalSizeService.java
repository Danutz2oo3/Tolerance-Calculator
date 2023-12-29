package com.Danutz.tolerancecalculator.nominalSize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NominalSizeService {
    private final NominalSizeRepository nominalSizeRepository;
    @Autowired
    public NominalSizeService(NominalSizeRepository nominalSizeRepository) {
        this.nominalSizeRepository = nominalSizeRepository;
    }

    public NominalSizeEntity getNominalDimension(Float nominalDimension){
        return nominalSizeRepository.findByNominalDimension(nominalDimension);
    }
    public Long getNominalDimensionId(Float nominalDimension){
        return nominalSizeRepository.findIdByNominalDimension(nominalDimension);
    }
}

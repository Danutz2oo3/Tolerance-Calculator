package com.Danutz.tolerancecalculator.borehole;

import com.Danutz.tolerancecalculator.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BoreHoleService {
    private final BoreHoleRepository boreHoleRepository;

    @Autowired
    public BoreHoleService(BoreHoleRepository boreHoleRepository) {
        this.boreHoleRepository = boreHoleRepository;
    }

    public List<BoreHoleEntity> getBoreHole(Long id){
        return boreHoleRepository.findALLBoreHoleEntityByName(id);
    }

    public BoreHoleEntity addBoreHole(BigDecimal nominalDimension, BigDecimal upperLimitDeviation, BigDecimal lowerLimitDeviation, UserEntity user){
        BoreHoleEntity boreHole = new BoreHoleEntity();
        boreHole.setNominalDimension(nominalDimension);
        boreHole.setUpperLimitDeviation(upperLimitDeviation);
        boreHole.setLowerLimitDeviation(lowerLimitDeviation);
        boreHole.setUserEntity(user);
        return boreHoleRepository.save(boreHole);
    }
}

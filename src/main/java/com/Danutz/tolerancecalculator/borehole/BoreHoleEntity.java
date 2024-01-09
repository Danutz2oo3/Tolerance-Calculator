package com.Danutz.tolerancecalculator.borehole;

import com.Danutz.tolerancecalculator.user.UserEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class BoreHoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)

    private Long id;

    private BigDecimal nominalDimension;
    private BigDecimal upperLimitDeviation;
    private BigDecimal lowerLimitDeviation;

    @ManyToOne
    @JoinColumn(name = "user_entity_id")
    private UserEntity userEntity;

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public BoreHoleEntity(BigDecimal nominalDimension, BigDecimal upperLimitDeviation, BigDecimal lowerLimitDeviation) {
        this.nominalDimension = nominalDimension;
        this.upperLimitDeviation = upperLimitDeviation;
        this.lowerLimitDeviation = lowerLimitDeviation;
    }

    public BoreHoleEntity(BigDecimal nominalDimension, BigDecimal upperLimitDeviation, BigDecimal lowerLimitDeviation, UserEntity userEntity) {
        this.nominalDimension = nominalDimension;
        this.upperLimitDeviation = upperLimitDeviation;
        this.lowerLimitDeviation = lowerLimitDeviation;
        this.userEntity = userEntity;
    }

    public BoreHoleEntity() {

    }

    public BigDecimal getUpperLimitDeviation() {
        return upperLimitDeviation;
    }

    public void setUpperLimitDeviation(BigDecimal upperLimitDeviation) {
        this.upperLimitDeviation = upperLimitDeviation;
    }

    public BigDecimal getLowerLimitDeviation() {
        return lowerLimitDeviation;
    }

    public void setLowerLimitDeviation(BigDecimal lowerLimitDeviation) {
        this.lowerLimitDeviation = lowerLimitDeviation;
    }

    public BigDecimal getNominalDimension() {
        return nominalDimension;
    }

    public void setNominalDimension(BigDecimal nominalDimension) {
        this.nominalDimension = nominalDimension;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

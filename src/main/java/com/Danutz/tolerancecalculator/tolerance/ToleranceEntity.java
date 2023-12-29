package com.Danutz.tolerancecalculator.tolerance;

import jakarta.persistence.*;

import java.util.Objects;

import com.Danutz.tolerancecalculator.nominalSize.NominalSizeEntity;
import com.Danutz.tolerancecalculator.toleranceClass.ToleranceClassEntity;

@Entity
public class ToleranceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "TC_SA_id", referencedColumnName = "SA_id"), // Referencing ToleranceClassEntity's column "SA_id"
            @JoinColumn(name = "TC_GoT_id", referencedColumnName = "GoT_id")  // Referencing ToleranceClassEntity's column "GoT_id"
    })
    private ToleranceClassEntity toleranceClass;
    @ManyToOne
    @JoinColumn(name = "nominal_size_entity_id")
    private NominalSizeEntity nominalSize;
    private Float upperLimitDeviation;
    private Float lowerLimitDeviation;

    public ToleranceEntity(ToleranceClassEntity toleranceClass, NominalSizeEntity nominalSize, Float upperLimitDeviation, Float lowerLimitDeviation) {
        this.toleranceClass = toleranceClass;
        this.nominalSize = nominalSize;
        this.upperLimitDeviation = upperLimitDeviation;
        this.lowerLimitDeviation = lowerLimitDeviation;
    }
    public ToleranceEntity() {

    }

    public NominalSizeEntity getNominalSizeEntity() {
        return nominalSize;
    }

    public void setNominalSizeEntity(NominalSizeEntity nominalSizeEntity) {
        this.nominalSize = nominalSizeEntity;
    }

    public ToleranceClassEntity getToleranceClassEntity() {
        return toleranceClass;
    }

    public void setToleranceClassEntity(ToleranceClassEntity toleranceClassEntity) {
        this.toleranceClass = toleranceClassEntity;
    }

    public Float getUpperLimitDeviation() {
        return upperLimitDeviation;
    }

    public void setUpperLimitDeviation(Float upperLimitDeviation) {
        this.upperLimitDeviation = upperLimitDeviation;
    }

    public Float getLowerLimitDeviation() {
        return lowerLimitDeviation;
    }

    public void setLowerLimitDeviation(Float lowerLimitDeviation) {
        this.lowerLimitDeviation = lowerLimitDeviation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToleranceEntity that = (ToleranceEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(toleranceClass, that.toleranceClass) && Objects.equals(nominalSize, that.nominalSize) && Objects.equals(upperLimitDeviation, that.upperLimitDeviation) && Objects.equals(lowerLimitDeviation, that.lowerLimitDeviation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, toleranceClass, nominalSize, upperLimitDeviation, lowerLimitDeviation);
    }

    @Override
    public String toString() {
        return "ToleranceEntity{" +
                "id=" + id +
                ", toleranceClass=" + toleranceClass +
                ", nominalSize=" + nominalSize +
                ", upperLimitDeviation=" + upperLimitDeviation +
                ", lowerLimitDeviation=" + lowerLimitDeviation +
                '}';
    }
}

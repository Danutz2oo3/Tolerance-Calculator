package com.de.guehring.tolerancecalculator.nominalSize;

import com.de.guehring.tolerancecalculator.tolerance.ToleranceEntity;
import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "nominal_size_entity")
public class NominalSizeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private Float upperBound;
    private Float lowerBound;

    @OneToMany(mappedBy = "nominalSize", orphanRemoval = true)
    private Set<ToleranceEntity> toleranceEntities = new LinkedHashSet<>();

    public Set<ToleranceEntity> getToleranceEntities() {
        return toleranceEntities;
    }

    public void setToleranceEntities(Set<ToleranceEntity> toleranceEntities) {
        this.toleranceEntities = toleranceEntities;
    }

    public NominalSizeEntity(Float upperBound, Float lowerBound) {
        this.upperBound = upperBound;
        this.lowerBound = lowerBound;
    }
    public NominalSizeEntity() {

    }
    public Float getUpperBound() {
        return upperBound;
    }

    public void setUpperBound(Float upperBound) {
        this.upperBound = upperBound;
    }

    public Float getLowerBound() {
        return lowerBound;
    }

    public void setLowerBound(Float lowerBound) {
        this.lowerBound = lowerBound;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NominalSizeEntity that = (NominalSizeEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(upperBound, that.upperBound) && Objects.equals(lowerBound, that.lowerBound);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, upperBound, lowerBound);
    }

    @Override
    public String toString() {
        return "NominalSize{" +
                "id=" + id +
                ", upperBound=" + upperBound +
                ", lowerBound=" + lowerBound +
                '}';
    }
}
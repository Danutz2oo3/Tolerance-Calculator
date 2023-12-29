package com.Danutz.tolerancecalculator.gradeOfTolerance;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

import com.Danutz.tolerancecalculator.toleranceClass.ToleranceClassEntity;

@Entity
@Table(name = "grade_of_tolerance_entity")
public class GradeOfToleranceEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)



    private Long id;
    private Float min;
    private Float max;
    private String name;

    @OneToMany(mappedBy = "gradeOfTolerance")
    private Set<ToleranceClassEntity> toleranceClassEntities = new LinkedHashSet<>();

    public Set<ToleranceClassEntity> getToleranceClassEntities() {
        return toleranceClassEntities;
    }

    public void setToleranceClassEntities(Set<ToleranceClassEntity> toleranceClassEntities) {
        this.toleranceClassEntities = toleranceClassEntities;
    }

    public GradeOfToleranceEntity(Float min, Float max, String name) {
        this.min = min;
        this.max = max;
        this.name = name;
    }
    public GradeOfToleranceEntity() {

    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getMin() {
        return min;
    }

    public void setMin(Float min) {
        this.min = min;
    }

    public Float getMax() {
        return max;
    }

    public void setMax(Float max) {
        this.max = max;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GradeOfToleranceEntity that = (GradeOfToleranceEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(min, that.min) && Objects.equals(max, that.max) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, min, max, name);
    }

    @Override
    public String toString() {
        return "GradeOfToleranceEntity{" +
                "id=" + id +
                ", min=" + min +
                ", max=" + max +
                ", name='" + name + '\'' +
                '}';
    }
}
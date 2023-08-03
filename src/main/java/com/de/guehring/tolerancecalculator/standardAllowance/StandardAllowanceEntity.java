package com.de.guehring.tolerancecalculator.standardAllowance;

import com.de.guehring.tolerancecalculator.standardAllowance.Type;
import com.de.guehring.tolerancecalculator.toleranceClass.ToleranceClassEntity;
import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "standard_allowance_entity")
public class StandardAllowanceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private Type type;
    private Float max;
    private Float min;

    @OneToMany(mappedBy = "standardAllowance", orphanRemoval = true)
    private Set<ToleranceClassEntity> toleranceClassEntities = new LinkedHashSet<>();

    public Set<ToleranceClassEntity> getToleranceClassEntities() {
        return toleranceClassEntities;
    }

    public void setToleranceClassEntities(Set<ToleranceClassEntity> toleranceClassEntities) {
        this.toleranceClassEntities = toleranceClassEntities;
    }

    public StandardAllowanceEntity(String name, Type type, Float max, Float min) {
        this.name = name;
        this.max = max;
        this.type = type;
        this.min=min;
    }
    public StandardAllowanceEntity() {

    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Float getMax() {
        return max;
    }
    public void setMax(Float max) {
        this.max = max;
    }

    public Float getMin() {
        return min;
    }

    public void setMin(Float min) {
        this.min = min;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StandardAllowanceEntity that = (StandardAllowanceEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && type == that.type && Objects.equals(max, that.max) && Objects.equals(min, that.min);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, max, min);
    }

    @Override
    public String toString() {
        return "StandardAllowanceEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", max=" + max +
                ", min=" + min +
                '}';
    }
}

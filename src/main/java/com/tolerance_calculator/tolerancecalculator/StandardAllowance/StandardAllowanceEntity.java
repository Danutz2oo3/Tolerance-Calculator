package com.tolerance_calculator.tolerancecalculator.StandardAllowance;

import com.tolerance_calculator.tolerancecalculator.entity.Type;
import jakarta.persistence.*;

import java.util.Objects;

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
    private Long max;
    private Long min;
    public StandardAllowanceEntity(String name, Type type, Long max, Long min) {
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

    public Long getMax() {
        return max;
    }
    public void setMax(Long max) {
        this.max = max;
    }

    public Long getMin() {
        return min;
    }

    public void setMin(Long min) {
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

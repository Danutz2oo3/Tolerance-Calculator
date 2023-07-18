package com.de.guehring.tolerancecalculator.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "nominal_size")
public class NominalSize {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private Integer upperBound;
    private Integer lowerBound;

    public NominalSize(Integer upperBound, Integer lowerBound) {
        this.upperBound = upperBound;
        this.lowerBound = lowerBound;
    }
    public NominalSize() {

    }
    public Integer getUpperBound() {
        return upperBound;
    }

    public void setUpperBound(Integer upperBound) {
        this.upperBound = upperBound;
    }

    public Integer getLowerBound() {
        return lowerBound;
    }

    public void setLowerBound(Integer lowerBound) {
        this.lowerBound = lowerBound;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NominalSize that = (NominalSize) o;
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
package com.tolerance_calculator.tolerancecalculator.GradeOfTolerance;

import com.tolerance_calculator.tolerancecalculator.StandardAllowance.StandardAllowanceEntity;
import com.tolerance_calculator.tolerancecalculator.entity.Type;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "grade_of_tolerance_entity")
public class GradeOfToleranceEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private Short min;
    private Long max;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
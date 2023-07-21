package com.de.guehring.tolerancecalculator.toleranceClass;

import com.de.guehring.tolerancecalculator.gradeOfTolerance.GradeOfToleranceEntity;
import com.de.guehring.tolerancecalculator.standardAllowance.StandardAllowanceEntity;
import com.de.guehring.tolerancecalculator.tolerance.ToleranceEntity;
import jakarta.persistence.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@IdClass(ToleranceClassId.class)
public class ToleranceClassEntity {

    @Id
    @ManyToOne
    @JoinColumn(name = "SA_id")
    private StandardAllowanceEntity standardAllowance;

    @Id
    @ManyToOne
    @JoinColumn(name = "GoT_id")
    private GradeOfToleranceEntity gradeOfTolerance;

    private Long max;

    private Long min;

    @OneToMany(mappedBy = "toleranceClass", orphanRemoval = true)
    private Set<ToleranceEntity> toleranceEntities = new LinkedHashSet<>();

    public Set<ToleranceEntity> getToleranceEntities() {
        return toleranceEntities;
    }

    public void setToleranceEntities(Set<ToleranceEntity> toleranceEntities) {
        this.toleranceEntities = toleranceEntities;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        ToleranceClassEntity that = (ToleranceClassEntity) o;
        return standardAllowance != null && Objects.equals(standardAllowance, that.standardAllowance)
                && gradeOfTolerance != null && Objects.equals(gradeOfTolerance, that.gradeOfTolerance);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(standardAllowance, gradeOfTolerance);
    }
}
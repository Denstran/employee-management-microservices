package org.example.administrationservice.model.department;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.administrationservice.event.department.DepartmentInfoBudgetReduced;
import org.example.administrationservice.event.department.DepartmentInfoRegistered;
import org.example.administrationservice.model.CompanyBranchDepartmentPK;
import org.example.administrationservice.model.Money;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.util.Objects;

@Entity
@Table(name = "DEPARTMENT_INFO")
@Getter
@Setter
public class DepartmentInfo extends AbstractAggregateRoot<DepartmentInfo> {

    @EmbeddedId
    private CompanyBranchDepartmentPK pk;

    @Embedded
    @AttributeOverride(name = "amount", column = @Column(name = "DEPARTMENT_BUDGET"))
    private Money departmentBudget;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DepartmentInfo that)) return false;
        return Objects.equals(pk, that.pk);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pk);
    }

    public void registerDepartmentInfo() {
        registerEvent(new DepartmentInfoRegistered(
                pk.getDepartment().getId(),
                pk.getCompanyBranch().getId(),
                departmentBudget));
    }

    public void reduceBudget(Money reduceAmount) {
        registerEvent(new DepartmentInfoBudgetReduced(
                pk.getDepartment().getId(),
                pk.getCompanyBranch().getId(),
                reduceAmount));
    }

    public void adjustBudget(Money adjustmentAmount) {
        registerEvent(new DepartmentInfoBudgetReduced(
                pk.getDepartment().getId(),
                pk.getCompanyBranch().getId(),
                adjustmentAmount));
    }
}

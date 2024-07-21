package org.example.administrationservice.event.department;

import lombok.Getter;
import lombok.Setter;
import org.example.administrationservice.model.Money;

@Getter
@Setter
public class DepartmentInfoRegistered extends AbstractDepartmentInfoEvent {
    private final Money departmentBudget;

    public DepartmentInfoRegistered(Long companyBranchId, Long departmentId, Money departmentBudget) {
        super(companyBranchId, departmentId);
        this.departmentBudget = departmentBudget;
    }
}

package org.example.administrationservice.event.department;

import lombok.Getter;
import lombok.Setter;
import org.example.administrationservice.model.Money;

@Getter
@Setter
public class DepartmentInfoRemoved extends AbstractDepartmentInfoEvent {
    private final Money departmentInfoBudget;

    public DepartmentInfoRemoved(Long companyBranchId, Long departmentId, Money departmentInfoBudget) {
        super(companyBranchId, departmentId);
        this.departmentInfoBudget = departmentInfoBudget;
    }
}

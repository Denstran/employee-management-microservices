package org.example.administrationservice.event.department;

import lombok.Getter;
import org.example.administrationservice.model.Money;

@Getter
public class DepartmentInfoBudgetAdjusted extends AbstractDepartmentInfoEvent {
    private final Money adjustmentAmount;

    public DepartmentInfoBudgetAdjusted(Long companyBranchId, Long departmentId, Money adjustmentAmount) {
        super(companyBranchId, departmentId);
        this.adjustmentAmount = adjustmentAmount;
    }
}

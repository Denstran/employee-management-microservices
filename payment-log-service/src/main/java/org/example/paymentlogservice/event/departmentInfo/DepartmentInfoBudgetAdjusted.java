package org.example.paymentlogservice.event.departmentInfo;

import lombok.Getter;
import org.example.paymentlogservice.dto.Money;

@Getter
public class DepartmentInfoBudgetAdjusted extends AbstractDepartmentInfoEvent {
    private final Money adjustmentAmount;

    public DepartmentInfoBudgetAdjusted(Long companyBranchId, Long departmentId, Money adjustmentAmount) {
        super(companyBranchId, departmentId);
        this.adjustmentAmount = adjustmentAmount;
    }
}

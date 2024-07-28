package org.example.paymentlogservice.event.departmentInfo;

import lombok.Getter;
import lombok.Setter;
import org.example.paymentlogservice.dto.Money;

@Getter
@Setter
public class DepartmentInfoBudgetReduced extends AbstractDepartmentInfoEvent {
    private final Money reduceAmount;

    public DepartmentInfoBudgetReduced(Long companyBranchId, Long departmentId, Money reduceAmount) {
        super(companyBranchId, departmentId);
        this.reduceAmount = reduceAmount;
    }
}

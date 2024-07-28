package org.example.paymentlogservice.event.departmentInfo;

import lombok.Getter;
import lombok.Setter;
import org.example.paymentlogservice.dto.Money;

@Getter
@Setter
public class DepartmentInfoRegistered extends AbstractDepartmentInfoEvent {
    private final Money departmentBudget;

    public DepartmentInfoRegistered(Long companyBranchId, Long departmentId, Money departmentBudget) {
        super(companyBranchId, departmentId);
        this.departmentBudget = departmentBudget;
    }
}

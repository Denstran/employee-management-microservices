package org.example.paymentlogservice.event.companyBranch;

import lombok.Getter;
import org.example.paymentlogservice.dto.Money;

@Getter
public class CompanyBranchBudgetAdjusted extends AbstractCompanyBranchEvent{
    private final Money adjustmentAmount;

    public CompanyBranchBudgetAdjusted(Long companyBranchId, Money adjustmentAmount) {
        super(companyBranchId);
        this.adjustmentAmount = adjustmentAmount;
    }
}

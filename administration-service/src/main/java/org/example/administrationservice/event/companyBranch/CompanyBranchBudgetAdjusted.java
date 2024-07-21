package org.example.administrationservice.event.companyBranch;

import lombok.Getter;
import org.example.administrationservice.model.Money;

@Getter
public class CompanyBranchBudgetAdjusted extends AbstractCompanyBranchEvent{
    private final Money adjustmentAmount;

    public CompanyBranchBudgetAdjusted(Long companyBranchId, Money adjustmentAmount) {
        super(companyBranchId);
        this.adjustmentAmount = adjustmentAmount;
    }
}

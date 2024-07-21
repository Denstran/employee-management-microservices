package org.example.administrationservice.event.companyBranch;

import lombok.Getter;
import org.example.administrationservice.model.Money;

@Getter
public class CompanyBranchBudgetReduced extends AbstractCompanyBranchEvent{
    private final Money adjustmentAmount;

    public CompanyBranchBudgetReduced(Long companyBranchId, Money adjustmentAmount) {
        super(companyBranchId);
        this.adjustmentAmount = adjustmentAmount;
    }
}

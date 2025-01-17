package org.example.administrationservice.event.companyBranch;

import lombok.Getter;
import org.example.administrationservice.model.Money;

@Getter
public class CompanyBranchBudgetReduced extends AbstractCompanyBranchEvent{
    private final Money reducedAmount;

    public CompanyBranchBudgetReduced(Long companyBranchId, Money reducedAmount) {
        super(companyBranchId);
        this.reducedAmount = reducedAmount;
    }
}

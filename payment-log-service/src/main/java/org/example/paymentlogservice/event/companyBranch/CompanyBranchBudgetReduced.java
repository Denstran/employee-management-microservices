package org.example.paymentlogservice.event.companyBranch;

import lombok.Getter;
import org.example.paymentlogservice.dto.Money;

@Getter
public class CompanyBranchBudgetReduced extends AbstractCompanyBranchEvent {
    private final Money reducedAmount;

    public CompanyBranchBudgetReduced(Long companyBranchId, Money reducedAmount) {
        super(companyBranchId);
        this.reducedAmount = reducedAmount;
    }
}

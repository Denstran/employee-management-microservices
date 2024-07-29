package org.example.paymentlogservice.event.companyBranch;

import lombok.Getter;
import org.example.paymentlogservice.dto.Money;

@Getter
public class CompanyBranchCreated extends AbstractCompanyBranchEvent {
    private final Money budget;

    public CompanyBranchCreated(Long companyBranchId, Money budget) {
        super(companyBranchId);
        this.budget = budget;
    }
}
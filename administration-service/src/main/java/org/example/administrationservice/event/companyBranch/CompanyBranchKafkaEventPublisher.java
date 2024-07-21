package org.example.administrationservice.event.companyBranch;

import org.springframework.stereotype.Component;

@Component
public class CompanyBranchKafkaEventPublisher {
    public void publishCompanyBranchCreatedEvent(CompanyBranchCreated companyBranchCreated) {
        throw new UnsupportedOperationException("Not implemented operation yet");
    }

    public void publishCompanyBranchBudgetAdjustedEvent(CompanyBranchBudgetAdjusted companyBranchBudgetAdjusted) {
        throw new UnsupportedOperationException("Not implemented operation yet");
    }

    public void publishCompanyBranchBudgetReducedEvent(CompanyBranchBudgetReduced companyBranchBudgetReduced) {
        throw new UnsupportedOperationException("Not implemented operation yet");
    }
}

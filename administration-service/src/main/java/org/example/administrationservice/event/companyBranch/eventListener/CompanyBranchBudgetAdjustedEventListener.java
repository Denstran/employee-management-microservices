package org.example.administrationservice.event.companyBranch.eventListener;

import org.example.administrationservice.event.companyBranch.CompanyBranchBudgetAdjusted;
import org.example.administrationservice.event.companyBranch.CompanyBranchKafkaEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class CompanyBranchBudgetAdjustedEventListener {
    private final CompanyBranchKafkaEventPublisher kafkaEventPublisher;

    @Autowired
    public CompanyBranchBudgetAdjustedEventListener(CompanyBranchKafkaEventPublisher kafkaEventPublisher) {
        this.kafkaEventPublisher = kafkaEventPublisher;
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void companyBranchBudgetAdjustedEventHandler(CompanyBranchBudgetAdjusted event) {
        kafkaEventPublisher.publishCompanyBranchBudgetAdjustedEvent(event);
    }
}

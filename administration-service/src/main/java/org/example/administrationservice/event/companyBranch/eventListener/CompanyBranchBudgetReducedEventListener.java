package org.example.administrationservice.event.companyBranch.eventListener;

import org.example.administrationservice.event.companyBranch.CompanyBranchBudgetReduced;
import org.example.administrationservice.event.companyBranch.CompanyBranchKafkaEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class CompanyBranchBudgetReducedEventListener {
    private final CompanyBranchKafkaEventPublisher kafkaEventPublisher;

    @Autowired
    public CompanyBranchBudgetReducedEventListener(CompanyBranchKafkaEventPublisher kafkaEventPublisher) {
        this.kafkaEventPublisher = kafkaEventPublisher;
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void companyBranchBudgetReducedEventHandler(CompanyBranchBudgetReduced event) {
        kafkaEventPublisher.publishCompanyBranchBudgetReducedEvent(event);
    }
}

package org.example.administrationservice.event.companyBranch.eventListener;

import org.example.administrationservice.event.companyBranch.CompanyBranchCreated;
import org.example.administrationservice.event.companyBranch.CompanyBranchKafkaEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class CompanyBranchCreatedEventListener {
    private final CompanyBranchKafkaEventPublisher kafkaEventPublisher;

    @Autowired
    public CompanyBranchCreatedEventListener(CompanyBranchKafkaEventPublisher kafkaEventPublisher) {
        this.kafkaEventPublisher = kafkaEventPublisher;
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void companyBranchCreatedEventHandler(CompanyBranchCreated event) {
        kafkaEventPublisher.publishCompanyBranchCreatedEvent(event);
    }
}

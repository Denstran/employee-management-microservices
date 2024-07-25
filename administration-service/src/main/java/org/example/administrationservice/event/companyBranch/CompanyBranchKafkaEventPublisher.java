package org.example.administrationservice.event.companyBranch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class CompanyBranchKafkaEventPublisher {
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private static final String TOPIC = "companyBranchPaymentLog";

    @Autowired
    public CompanyBranchKafkaEventPublisher(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishCompanyBranchCreatedEvent(CompanyBranchCreated companyBranchCreated) {
        kafkaTemplate.send(TOPIC, "CompanyBranchCreated", companyBranchCreated);
    }

    public void publishCompanyBranchBudgetAdjustedEvent(CompanyBranchBudgetAdjusted companyBranchBudgetAdjusted) {
        kafkaTemplate.send(TOPIC, "CompanyBranchBudgetAdjusted", companyBranchBudgetAdjusted);
    }

    public void publishCompanyBranchBudgetReducedEvent(CompanyBranchBudgetReduced companyBranchBudgetReduced) {
        kafkaTemplate.send(TOPIC, "CompanyBranchBudgetReduced", companyBranchBudgetReduced);
    }
}

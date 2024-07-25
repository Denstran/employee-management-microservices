package org.example.administrationservice.event.department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class DepartmentInfoKafkaEventPublisher {
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private static final String TOPIC = "departmentInfoPaymentLog";

    @Autowired
    public DepartmentInfoKafkaEventPublisher(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishDepartmentInfoRegisteredEvent(DepartmentInfoRegistered event) {
        kafkaTemplate.send(TOPIC, "DepartmentInfoRegistered", event);
    }

    public void publishDepartmentInfoBudgetAdjustedEvent(DepartmentInfoBudgetAdjusted event) {
        kafkaTemplate.send(TOPIC, "DepartmentInfoBudgetAdjusted", event);
    }

    public void publishDepartmentInfoBudgetReducedEvent(DepartmentInfoBudgetReduced event) {
        kafkaTemplate.send(TOPIC, "DepartmentInfoBudgetReduced", event);
    }
    public void publishDepartmentInfoRemovedEvent(DepartmentInfoRemoved event) {
        kafkaTemplate.send(TOPIC, "DepartmentInfoRemoved", event);
    }
}

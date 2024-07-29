package org.example.administrationservice.event.department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DepartmentInfoKafkaEventPublisher {
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private static final String DEPARTMENT_INFO_PAYMENT_LOG_TOPIC = "departmentInfoPaymentLog";
    private static final String EMPLOYEE_EVENT_TOPIC = "employee";

    @Autowired
    public DepartmentInfoKafkaEventPublisher(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishDepartmentInfoRegisteredEvent(DepartmentInfoRegistered event) {
        kafkaTemplate.send(DEPARTMENT_INFO_PAYMENT_LOG_TOPIC, "DepartmentInfoRegistered", event);
    }

    public void publishDepartmentInfoBudgetAdjustedEvent(DepartmentInfoBudgetAdjusted event) {
        kafkaTemplate.send(DEPARTMENT_INFO_PAYMENT_LOG_TOPIC, "DepartmentInfoBudgetAdjusted", event);
    }

    public void publishDepartmentInfoBudgetReducedEvent(DepartmentInfoBudgetReduced event) {
        kafkaTemplate.send(DEPARTMENT_INFO_PAYMENT_LOG_TOPIC, "DepartmentInfoBudgetReduced", event);
    }

    public void publishDepartmentInfoRemovedEvent(List<Long> employeeIds) {
        kafkaTemplate.send(EMPLOYEE_EVENT_TOPIC, "DepartmentInfoRemoved", employeeIds);
    }
}

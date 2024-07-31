package org.example.paymentlogservice.event.employeePaymentLog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class EmployeePaymentLogEventListener {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    public EmployeePaymentLogEventListener(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void employeePaymentLogCreated(EmployeePaymentCreated event) {
        kafkaTemplate.send("email", "employee-payment-log-created", event);
    }
}

package org.example.taskservice.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class TaskEventListener {
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private static final String EMAIL_TOPIC = "email";

    @Autowired
    public TaskEventListener(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void taskCreatedEventHandler(TaskCreated taskCreated) {
        kafkaTemplate.send(EMAIL_TOPIC, "task_created", taskCreated);
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void taskFinishedEventHandler(TaskFinished taskFinished) {
        kafkaTemplate.send(EMAIL_TOPIC, "task_finished", taskFinished);
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void taskApprovedEventHandler(TaskApproved taskApproved) {
        kafkaTemplate.send(EMAIL_TOPIC, "task_approved", taskApproved);
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void taskCanceledEventHandler(TaskCanceled taskCanceled) {
        kafkaTemplate.send(EMAIL_TOPIC, "task_canceled", taskCanceled);
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void taskDisapprovedEventHandler(TaskDisapproved taskDisapproved) {
        kafkaTemplate.send(EMAIL_TOPIC, "task_disapproved", taskDisapproved);
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void taskDeadlineExtendedEventHandler(TaskDeadlineExtended taskDeadlineExtended) {
        kafkaTemplate.send(EMAIL_TOPIC, "task_deadline_extended", taskDeadlineExtended);
    }
}

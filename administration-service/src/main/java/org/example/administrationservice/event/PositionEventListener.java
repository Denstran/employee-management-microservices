package org.example.administrationservice.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class PositionEventListener {
    private final PositionKafkaEventPublisher kafkaEventPublisher;

    @Autowired
    public PositionEventListener(PositionKafkaEventPublisher kafkaEventPublisher) {
        this.kafkaEventPublisher = kafkaEventPublisher;
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void positionLeadingChangedEventHandler(PositionLeadingChanged event) {
        kafkaEventPublisher.publishPositionLeadingChangedEvent(event);
    }
}

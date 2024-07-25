package org.example.administrationservice.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class PositionKafkaEventPublisher {
    private static final String TOPIC = "position";

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    public PositionKafkaEventPublisher(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishPositionLeadingChangedEvent(PositionLeadingChanged event) {
        kafkaTemplate.send(TOPIC, "PositionLeadingChanged", event);
    }
}

package org.example.emailservice.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.example.emailservice.listener.eventHandler.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmailKafkaEventListener {
    private final List<EventHandler> eventHandlers;

    @Autowired
    public EmailKafkaEventListener(List<EventHandler> eventHandlers) {
        this.eventHandlers = eventHandlers;
    }

    @KafkaListener(topics = "email", groupId = "email")
    public void emailKafkaEventListener(ConsumerRecord<String, String> event) {
        String eventType = event.key();
        String eventJson = event.value();

        eventHandlers.forEach(eventHandler -> eventHandler.handleEvent(eventType, eventJson));
    }
}

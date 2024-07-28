package org.example.paymentlogservice.event.companyBranch;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.example.paymentlogservice.event.EventHandler;
import org.example.paymentlogservice.event.HandlerQualifier;
import org.example.paymentlogservice.service.CompanyBranchPaymentLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CompanyBranchKafkaEventListener {

    private final List<EventHandler> eventHandlers;

    @Autowired
    public CompanyBranchKafkaEventListener(@HandlerQualifier(handlerKey = "companyBranchPaymentEvent")
                                           List<EventHandler> eventHandlers) {
        this.eventHandlers = eventHandlers;
    }

    @KafkaListener(topics = "companyBranchPaymentLog", groupId = "payment-logs")
    public void companyBranchKafkaEventListener(ConsumerRecord<String, String> event) throws JsonProcessingException {
        String eventType = event.key();
        String eventJson = event.value();

        eventHandlers.forEach(eventHandler -> eventHandler.handleEvent(eventType, eventJson));
    }
}

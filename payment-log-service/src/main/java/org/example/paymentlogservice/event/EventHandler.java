package org.example.paymentlogservice.event;

public interface EventHandler {
    void handleEvent(String eventType, String event);
}

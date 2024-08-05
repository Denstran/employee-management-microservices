package org.example.emailservice.listener.eventHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.example.emailservice.model.Mail;
import org.example.emailservice.service.EmailService;

@Slf4j
public abstract class AbstractMailEventHandler<T> implements EventHandler {
    private final String KEY;
    private final ObjectMapper objectMapper;
    private final EmailService emailService;
    private final Class<T> eventClass;

    public AbstractMailEventHandler(String KEY,
                                    ObjectMapper objectMapper,
                                    EmailService emailService,
                                    Class<T> eventClass) {
        this.KEY = KEY;
        this.objectMapper = objectMapper;
        this.emailService = emailService;
        this.eventClass = eventClass;
    }

    @Override
    public void handleEvent(String eventType, String event) {
        if (!KEY.equals(eventType)) return;
        log.info("Handling event: {}", KEY);

        try {
            T eventObject = objectMapper.readValue(event, eventClass);
            Mail mail = prepareMail(eventObject);
            emailService.sendEmail(mail);
        }catch (JsonProcessingException e) {
            log.error("Error during handling event {}, error message {}", eventType, e.getMessage());
            throw new RuntimeException(
                    String.format("Can't read event: %s for %s class", eventType, eventClass.getName()));
        }
    }

    protected abstract Mail prepareMail(T eventObject);
}

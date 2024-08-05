package org.example.emailservice.listener.eventHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.emailservice.dto.TaskDisapproved;
import org.example.emailservice.model.Mail;
import org.example.emailservice.service.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TaskDisapprovedEventHandler extends AbstractMailEventHandler<TaskDisapproved> {
    @Value("${spring.mail.username}")
    private String from;

    public TaskDisapprovedEventHandler(ObjectMapper objectMapper, EmailService emailService) {
        super("task_disapproved", objectMapper, emailService, TaskDisapproved.class);
    }

    @Override
    protected Mail prepareMail(TaskDisapproved eventObject) {
        String to = eventObject.getOwnerEmail();
        String subject = "Задача не прошла проверку";
        String text = String.format(
                """
                Задача "%s" не прошла проверку
                Контакты начальника: %s
                """, eventObject.getTaskDescription(), eventObject.getGiverEmail());

        return new Mail(from, to, subject, text);
    }
}

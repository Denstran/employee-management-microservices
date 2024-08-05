package org.example.emailservice.listener.eventHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.emailservice.dto.TaskFinished;
import org.example.emailservice.model.Mail;
import org.example.emailservice.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TaskFinishedEventHandler extends AbstractMailEventHandler<TaskFinished> {
    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    public TaskFinishedEventHandler(ObjectMapper objectMapper, EmailService emailService) {
        super("task_finished", objectMapper, emailService, TaskFinished.class);
    }

    @Override
    protected Mail prepareMail(TaskFinished eventObject) {
        String to = eventObject.getGiverEmail();
        String subject = "Поступила задача на проверку";
        String text = String.format(
                """
                Задача "%s" поступила на проверку
                Контакты сотрудника: %s
                """, eventObject.getTaskDescription(), eventObject.getOwnerEmail());

        return new Mail(from, to, subject, text);
    }
}

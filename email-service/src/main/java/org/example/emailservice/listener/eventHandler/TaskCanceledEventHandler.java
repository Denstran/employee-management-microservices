package org.example.emailservice.listener.eventHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.example.emailservice.dto.TaskCanceled;
import org.example.emailservice.model.Mail;
import org.example.emailservice.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TaskCanceledEventHandler extends AbstractMailEventHandler<TaskCanceled> {
    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    public TaskCanceledEventHandler(ObjectMapper objectMapper, EmailService emailService) {
        super("task_canceled", objectMapper, emailService, TaskCanceled.class);
    }

    @Override
    protected Mail prepareMail(TaskCanceled taskCanceled) {
        String to = taskCanceled.getTaskOwnerEmail();
        String subject = "Отмена задачи";
        String text = String.format(
                """
                Задача: "%s" была отменена
                """, taskCanceled.getTaskDescription());

        return new Mail(from, to, subject, text);
    }
}

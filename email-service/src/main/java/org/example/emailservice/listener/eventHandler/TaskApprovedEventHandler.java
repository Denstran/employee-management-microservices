package org.example.emailservice.listener.eventHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.example.emailservice.dto.TaskApproved;
import org.example.emailservice.model.Mail;
import org.example.emailservice.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TaskApprovedEventHandler extends AbstractMailEventHandler<TaskApproved> {
    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    public TaskApprovedEventHandler(ObjectMapper objectMapper, EmailService emailService) {
        super("task_approved", objectMapper, emailService, TaskApproved.class);
    }

    @Override
    protected Mail prepareMail(TaskApproved taskApproved) {
        String to = taskApproved.getOwnerEmail();
        String text = String.format(
                """
                Задача %s прошла проверку и завершена.
                """, taskApproved.getTaskDescription());
        return new Mail(from, to, "Подтверждение выполнения задачи", text);
    }
}

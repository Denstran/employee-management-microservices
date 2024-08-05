package org.example.emailservice.listener.eventHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.example.emailservice.dto.TaskCreated;
import org.example.emailservice.model.Mail;
import org.example.emailservice.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TaskCreatedEventHandler extends AbstractMailEventHandler<TaskCreated> {
    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    public TaskCreatedEventHandler(ObjectMapper objectMapper, EmailService emailService) {
        super("task_created", objectMapper, emailService, TaskCreated.class);
    }

    @Override
    protected Mail prepareMail(TaskCreated taskCreated) {
        String to = taskCreated.getEmployeeEmail();
        String subject = "Новая задача";
        String text = String.format(
                """
                Вам поступила новая задача: "%s"
                Срок сдачи: %s
                Почта начальника: %s
                """, taskCreated.getEmployeeEmail(), taskCreated.getTaskDeadLine(), taskCreated.getGiverEmail());

        return new Mail(from, to, subject, text);
    }
}

package org.example.emailservice.listener.eventHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.example.emailservice.dto.TaskDeadlineExtended;
import org.example.emailservice.model.Mail;
import org.example.emailservice.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TaskDeadlineExtendedEventHandler extends AbstractMailEventHandler<TaskDeadlineExtended> {
    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    public TaskDeadlineExtendedEventHandler(ObjectMapper objectMapper, EmailService emailService) {
        super("task_deadline_extended", objectMapper, emailService, TaskDeadlineExtended.class);
    }

    @Override
    protected Mail prepareMail(TaskDeadlineExtended taskDeadlineExtended) {
        String to = taskDeadlineExtended.getOwnerEmail();
        String subject = "Срок выполнения задачи продлён";
        String text = String.format(
                """
                Задача "%s" продлена до %s
                Почта начальника %s
                """,
                taskDeadlineExtended.getTaskDescription(),
                taskDeadlineExtended.getExtendedDeadline(),
                taskDeadlineExtended.getGiverEmail());

        return new Mail(from, to, subject, text);
    }
}

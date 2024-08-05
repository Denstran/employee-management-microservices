package org.example.emailservice.service;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.example.emailservice.model.Mail;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Getter
@Service
@Slf4j
public class EmailService {
    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Async
    public void sendEmail(Mail mail) {
        log.info("SENDING MAIL TO {}, FROM {}, SUBJECT: {}, TEXT {}",
                mail.getTo(), mail.getFrom(), mail.getSubject(), mail.getText());
        SimpleMailMessage message = mapMailToSimpleMailMessage(mail);
        log.info("MESSAGE PREPARED: {}", message);
        mailSender.send(message);
    }

    private SimpleMailMessage mapMailToSimpleMailMessage(Mail mail) {
        log.info("MAPPING Mail to SimpleMailMessage: Mail object: {}", mail);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mail.getFrom());
        message.setTo(mail.getTo());
        message.setSubject(mail.getSubject());
        message.setText(mail.getText());
        log.info("FINISHED MAPPING Mail to SimpleMailMessage: SimpleMailMessage object {}", message);
        return message;
    }
}

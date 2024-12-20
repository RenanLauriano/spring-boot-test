package com.javatest.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);
    private static final String EMAIL_SUBJECT = "User update - Differences with previous data copy";

    private final JavaMailSender mailSender;

    @Value("${application.toEmail}")
    private String toEmail;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(String text) {
        logger.info("Building the email message");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject(EMAIL_SUBJECT);
        message.setText(text);
        message.setFrom("noreply@example.com");
        mailSender.send(message);
        logger.info("Email sent with subject ({}) and content ({})", EMAIL_SUBJECT, text);
    }
}
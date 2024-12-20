package com.javatest.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

public class EmailServiceTest {

    @Mock
    private JavaMailSender mailSender;

    @InjectMocks
    private EmailService emailService;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        setEmailServiceToEmail("test@example.com");
    }

    private void setEmailServiceToEmail(String toEmail) throws Exception {
        Field field = EmailService.class.getDeclaredField("toEmail");
        field.setAccessible(true);
        field.set(emailService, toEmail);
    }

    @Test
    public void testSendEmail() {
        String text = "Test email content";

        emailService.sendEmail(text);

        ArgumentCaptor<SimpleMailMessage> messageCaptor = ArgumentCaptor.forClass(SimpleMailMessage.class);
        verify(mailSender).send(messageCaptor.capture());

        SimpleMailMessage capturedMessage = messageCaptor.getValue();
        assertEquals("test@example.com", capturedMessage.getTo()[0]);
        assertEquals("User update - Differences with previous data copy", capturedMessage.getSubject());
        assertEquals(text, capturedMessage.getText());
        assertEquals("noreply@example.com", capturedMessage.getFrom());
    }
}
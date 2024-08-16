package com.akasa.food.order.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String to, String subject, String body) {

        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom("jabhinath1995@gmail.com");
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(body);

        try {
          // javaMailSender.send(mailMessage);

        } catch (Exception e) {
            log.error("Error while sending mail", e);
        }
        log.info("Email sent successfully");
    }
}

package com.xworkz.hospital.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.CompletableFuture;



@Service
@PropertySource("classpath:application.properties")
public class EmailService {

    @Value("${spring.mail.username}")
    private String hospitalEmail;
    @Autowired
    private JavaMailSender emailSender;



    @Async
    public void getEmail(String email, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(hospitalEmail);
        message.setTo(email);
        message.setSubject(subject);
        message.setText(body);
        emailSender.send(message);

    }


    @Async
    public CompletableFuture<Boolean> sendContact(String email, String subject, String body) {
        try {
            if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                return CompletableFuture.completedFuture(false);
            }
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(email);
            message.setTo(hospitalEmail);
            message.setSubject(subject + "-" + email);
            message.setText(body);
            message.setSentDate(new Date());
            message.setReplyTo(email);
            emailSender.send(message);
            return CompletableFuture.completedFuture(true);
        }catch (MailException e){
            e.printStackTrace();
           return CompletableFuture.completedFuture(false);
        }
    }

}

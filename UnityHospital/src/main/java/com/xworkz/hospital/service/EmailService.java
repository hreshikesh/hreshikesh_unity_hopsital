package com.xworkz.hospital.service;


import lombok.Data;
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
import java.util.concurrent.Future;


@Service
@PropertySource("classpath:application.properties")
public class EmailService {

    @Value("${spring.mail.username}")
    private String userEmail;
    @Autowired
    private JavaMailSender emailSender;



    @Async
    public void getEmail(String email, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(userEmail);
        message.setTo(email);
        message.setSubject(subject);
        message.setText(body);
        emailSender.send(message);

    }


    @Async
    public CompletableFuture<Boolean> sendContact(String email, String subject, String body) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(userEmail);
            message.setTo(userEmail);
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

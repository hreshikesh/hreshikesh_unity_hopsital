package com.xworkz.hospital.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


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


}

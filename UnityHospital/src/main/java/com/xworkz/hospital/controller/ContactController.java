package com.xworkz.hospital.controller;

import com.xworkz.hospital.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Controller
@RequestMapping("/")
public class ContactController {
    @Autowired
    EmailService emailService;

    @PostMapping("contact")
    public String contactHospital(String  fullName, String email, String message, String subject, Model model) throws ExecutionException, InterruptedException {
        if(fullName!=null && email!=null && message!=null && subject!=null) {
           CompletableFuture<Boolean> status= emailService.sendContact(email, subject, message + "\n\n" + "Fullname: " + fullName);
           if(Boolean.TRUE.equals(status.get())) {
               model.addAttribute("check", "Your Message has been sent .We will contact you soon");
           }else {
               model.addAttribute("check","There was a issue in sending your message .Check your mail or contact hospital");
           }
        }
        return "UserDashBoard";
    }

}

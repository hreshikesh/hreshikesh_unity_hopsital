package com.xworkz.hospital.controller;

import com.xworkz.hospital.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ContactController {
    @Autowired
    EmailService emailService;

    @PostMapping("contact")
    public String contactHospital(String  fullName, String email, String message, String subject, Model model){
        if(fullName!=null && email!=null && message!=null && subject!=null) {
            emailService.getEmail(email, subject, message + "\n\n" + "Fullname: " + fullName);
            model.addAttribute("check",true);
        }else{
            model.addAttribute("check",false);
        }
        return "UserDashBoard";

    }



}

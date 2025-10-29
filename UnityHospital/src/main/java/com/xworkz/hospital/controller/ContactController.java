package com.xworkz.hospital.controller;

import com.xworkz.hospital.dto.EventDto;
import com.xworkz.hospital.service.EmailService;
import com.xworkz.hospital.service.EventService;
import com.xworkz.hospital.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Controller
@RequestMapping("/")
public class ContactController {
    @Autowired
    EmailService emailService;

    @Autowired
    EventService eventService;

    @PostMapping("contact")
    public String contactHospital(String fullName, String email, String message, String subject, Model model, HttpSession httpSession) throws ExecutionException, InterruptedException {
        if (fullName != null && email != null && message != null && subject != null) {
            CompletableFuture<Boolean> status = emailService.sendContact(email, subject, message + "\n\n" + "Fullname: " + fullName);
            if (Boolean.TRUE.equals(status.get())) {
                model.addAttribute("check", "Your Message has been sent .We will contact you soon");
            } else {
                model.addAttribute("check", "There was a issue in sending your message .Check your mail or contact hospital");
            }
        }
        List<EventDto> eventDtos = eventService.getAllEvent();
        model.addAttribute("dtos", eventDtos);
        if (eventDtos == null || eventDtos.isEmpty()) {
            eventDtos.addAll(Arrays.asList(
                    new EventDto(0, "Welcome to Unity Hospital - Excellence in Healthcare", null),
                    new EventDto(0, "24/7 Emergency Services - Call +91-12345-67890 ", null),
                    new EventDto(0, "Book OPD Appointments Online - Quick & Easy", null),
                    new EventDto(0, "Get Affordable Health Checkup Packages Today", null),
                    new EventDto(0, "Unity Hospital - Caring Beyond Limits", null)
            ));
            model.addAttribute("dtos", eventDtos);
        }

        String userName=(String) httpSession.getAttribute("userName");
        model.addAttribute("user",userName);

        return "UserDashBoard";
    }
}

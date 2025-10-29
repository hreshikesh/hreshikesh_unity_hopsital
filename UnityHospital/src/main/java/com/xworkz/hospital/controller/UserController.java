package com.xworkz.hospital.controller;

import com.xworkz.hospital.dto.EventDto;
import com.xworkz.hospital.dto.UserDto;
import com.xworkz.hospital.service.EventService;
import com.xworkz.hospital.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    EventService eventService;

    @PostMapping("registerUser")
    public ModelAndView registerUser(@Valid UserDto userDto, BindingResult result, ModelAndView view) {
        if (result.hasErrors()) {
            view.setViewName("UserSignUP");
            view.addObject("dto", userDto);
            view.addObject("error", result.getAllErrors());
            return view;
        } else {

            boolean isSaved = userService.saveUser(userDto);
            if (isSaved) {
                view.setViewName("UserSignIn");
                return view;
            } else {
                view.setViewName("UserSignUP");
                view.addObject("dto", userDto);
                view.addObject("result", "User Details Not Saved");
                return view;
            }
        }
    }

    @PostMapping("sendOtp")
    public String verifyAndSendOtp(@RequestParam String email, Model model, HttpSession session) {
        if (email == null) {
            model.addAttribute("emailerror", "Enter email");
            model.addAttribute("email", email);
        } else {
            session.setAttribute("userEmail", email);
            String result = userService.verifyAndSendOtp(email);
            if (result.equals("Email Not Found")) {
                model.addAttribute("emailerror", "Email Not Found");
                model.addAttribute("email", email);
            } else if (result.equals("sentOtp")) {
                model.addAttribute("check", true);
                model.addAttribute("otpSent", true);
                model.addAttribute("email", email);
            }
        }
        return "UserSignIn";
    }

    @PostMapping("resendOtp")
    public String resendOtp(@RequestParam String email, Model model) {
        String result = userService.verifyAndSendOtp(email);
        if (result.equals("Email Not Found")) {
            model.addAttribute("emailerror", "Email Not Found");
            model.addAttribute("email", email);
        } else if (result.equals("sentOtp")) {
            model.addAttribute("check", true);
            model.addAttribute("otpSent", true);
            model.addAttribute("email", email);
        }
        return "UserSignIn";
    }

    @PostMapping("login")
    public String gotoUserDashBoard(Model model, HttpSession session) {
        String userEmail = (String) session.getAttribute("userEmail");
        UserDto userDto = userService.findByEmail(userEmail);
        if (userDto != null) {
            session.setAttribute("userName", userDto.getUserName());
            model.addAttribute("user", userDto.getUserName());
            userService.updateOTP(userEmail);
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
        return "UserDashBoard";
    }


    @GetMapping("profile")
    public String getUserProfile(Model model, HttpSession session) {
        String email = (String) session.getAttribute("userEmail");
        UserDto dto = userService.findByEmail(email);
        session.setAttribute("userName",dto.getUserName());
        model.addAttribute("dto", dto);
        return "UserProfile";
    }


    @GetMapping("updateProfile")
    public String userProfileUpdate(Model model, HttpSession session) {
        String email = (String) session.getAttribute("userEmail");
        UserDto dto = userService.findByEmail(email);
        model.addAttribute("dto", dto);
        return "UserUpdate";
    }

    @PostMapping("saveUpdatedProfile")
    public ModelAndView updateUserProfile(@Valid UserDto userDto, BindingResult result, ModelAndView view,HttpSession session) {
        if (result.hasErrors()) {
            view.setViewName("UserUpdate");
            view.addObject("dto", userDto);
            view.addObject("error", result.getAllErrors());
            return view;
        } else {
            boolean isUpdated = userService.updateUserDetails(userDto);
            if (isUpdated) {
                view.setViewName("UserUpdate");
                view.addObject("status", "success");
                UserDto userDto1 = userService.findByEmail(userDto.getUserEmail());
                session.setAttribute("userName",userDto1.getUserName());
                view.addObject("dto", userDto1);
            } else {
                view.setViewName("UserUpdate");
                view.addObject("status", "failure");
            }
        }
        return view;
    }
}

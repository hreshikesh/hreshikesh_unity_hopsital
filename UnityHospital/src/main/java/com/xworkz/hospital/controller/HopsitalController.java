package com.xworkz.hospital.controller;

import com.xworkz.hospital.service.HospitalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequestMapping("/")
public class HopsitalController {

    @Autowired
    HospitalService hospitalService;
    @RequestMapping("sendotp")
    public ModelAndView verifyAndSendOtp(String email, ModelAndView modelAndView, HttpSession session) {
        boolean check = hospitalService.findByEmail(email);
        session.setAttribute("adminEmail", email);
        modelAndView.addObject("email", email);
        modelAndView.addObject("check", check);
        modelAndView.setViewName("admin");
        return modelAndView;
    }
    @RequestMapping("adminEmail")
    public ModelAndView verifyOtpAndLogin(String otp, ModelAndView modelAndView) {
        boolean otpStatus = hospitalService.verifyOtp(otp);
        if (otpStatus) {
            modelAndView.setViewName("Home");
        } else {
            modelAndView.addObject("otpstatus", "fail");
            modelAndView.setViewName("admin");
        }
        return modelAndView;
    }

    @RequestMapping("resendOtp")
    public ModelAndView resendOtp(ModelAndView modelAndView, HttpSession httpSession) {
        hospitalService.sendOtp((String) httpSession.getAttribute("adminEmail"));
        modelAndView.addObject("email", httpSession.getAttribute("adminEmail"));
        modelAndView.addObject("check", true);
        modelAndView.setViewName("admin");
        return modelAndView;
    }
}

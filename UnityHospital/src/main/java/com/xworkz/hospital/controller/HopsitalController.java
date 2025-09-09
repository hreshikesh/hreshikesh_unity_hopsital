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

    @RequestMapping("adminEmail")
    public ModelAndView verifyOtpAndLogin(String otp, ModelAndView modelAndView,HttpSession session) {
        String otpStatus = hospitalService.verifyOtp(otp,(String) session.getAttribute("adminEmail"));
        if (otpStatus.equals("pass")) {
            modelAndView.setViewName("Home");
        }
        else if (otpStatus.equals("fail")) {
            modelAndView.addObject("otpstatus", "mismatch");
            modelAndView.addObject("email",session.getAttribute("adminEmail") );
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

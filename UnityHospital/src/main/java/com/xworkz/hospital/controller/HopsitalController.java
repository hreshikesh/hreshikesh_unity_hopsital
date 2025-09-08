package com.xworkz.hospital.controller;

import com.xworkz.hospital.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HopsitalController {
    @Autowired
    HospitalService hospitalService;
    @RequestMapping("sendotp")
    public ModelAndView sendOtp(String email,ModelAndView modelAndView) {
       boolean status= hospitalService.sendOtp(email);
       if (status){
           modelAndView.addObject("result","otp sent to email");
       }else{
           modelAndView.addObject("result","otp not sent");
       }
        modelAndView.setViewName("admin");
       return modelAndView;
    }
}

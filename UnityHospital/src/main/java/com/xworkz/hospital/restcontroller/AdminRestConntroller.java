package com.xworkz.hospital.restcontroller;

import com.xworkz.hospital.service.HospitalService;
import com.xworkz.hospital.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class AdminRestConntroller {
    @Autowired
    HospitalService service;


    @GetMapping("checkEmail/{email}")
    public String verifyEmail(@PathVariable String email) {
        int count = service.findEmail(email);
        if (count == 1) {
            return " ";
        } else return "Email Not Present";
    }

    @GetMapping("sendOtp/{email}")
    public String verifyAndSendOtp(@PathVariable String email, HttpSession session) {
        String sent = service.sendOtp(email);
        session.setAttribute("adminEmail1", email);
        if (sent.equals("true")) {
            return "OTP sent successfully";
        } else {
            return "OTP not sent check your Email!";
        }

    }

    @GetMapping("verifyOtp/{otp}")
    public String verifyOtp(@PathVariable String otp, HttpSession session) {
        String check = service.verifyOtp(otp, (String) session.getAttribute("adminEmail1"));
        return check;
    }

    @GetMapping("resetTimeOtp")
    public String resendOtp(HttpSession session) {
        service.sendOtp((String) session.getAttribute("adminEmail1"));
        return "OTP resent.";
    }


}

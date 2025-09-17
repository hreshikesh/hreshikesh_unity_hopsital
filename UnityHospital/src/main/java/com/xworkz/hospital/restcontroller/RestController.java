package com.xworkz.hospital.restcontroller;

import com.xworkz.hospital.service.HospitalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpSession;
@Slf4j
@org.springframework.web.bind.annotation.RestController
@RequestMapping("/")
public class RestController {
    @Autowired
    HospitalService service;
    @GetMapping("checkEmail/{email}")
    public String verifyEmail(@PathVariable String email){
        int count=service.findEmail(email);
        if (count==1){
            return " ";
        }else return "Email Not Present";
    }

    @GetMapping("sendOtp/{email}")
    public String verifyAndSendOtp(@PathVariable String email,HttpSession session){
        boolean sent= service.sendOtp(email);
        session.setAttribute("adminEmail1",email);
        if(sent){
            return "OTP sent successfully";
        }else{
            return "OTP not sent";
        }

    }

    @GetMapping("verifyOtp/{otp}")
    public String verifyOtp(@PathVariable String otp,HttpSession session){
        String check=service.verifyOtp(otp,(String) session.getAttribute("adminEmail1"));
        return check;
    }

    @GetMapping("resetTimeOtp")
    public String resendOtp(HttpSession session){
        service.sendOtp((String) session.getAttribute("adminEmail1"));
        return "OTP resent.";
    }

    @PostMapping("resetSession")
    public void resetSession(HttpSession session){
        session.setAttribute("adminLoggedIn",false);
        session.removeAttribute("adminEmail");
    }


    @GetMapping("checkDoctorEmail/{doctorEmail}")
    public String checkDoctorEmail(@PathVariable String doctorEmail){
       long count= service.getEmailCount(doctorEmail);
       if(count==0L){
           return "";
       }else {
           return "Email already exists Present";
       }
    }

    @GetMapping("checkInterval/{specialization}/{timeInterval}")
    public String checkIntervalPresent(@PathVariable String specialization,@PathVariable String timeInterval){
        int count=service.checkIntervalForSpecification(specialization,timeInterval);
        log.info(specialization);
        log.info(String.valueOf(count));
        if(count>=1){
            return "Booked";
        }else{
            return "available";
        }

    }


}

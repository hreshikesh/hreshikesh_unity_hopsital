package com.xworkz.hospital.restcontroller;

import com.xworkz.hospital.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpSession;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/")
public class RestController {
    @Autowired
    HospitalService service;
    @GetMapping("checkEmail/{email}")
    public String verifyEmail(@PathVariable String email, HttpSession httpSession){
        int count=service.findEmail(email);
        if (count==1){
            httpSession.setAttribute("email",email);
            return " ";
        }else return "Email Not Present";
    }
}

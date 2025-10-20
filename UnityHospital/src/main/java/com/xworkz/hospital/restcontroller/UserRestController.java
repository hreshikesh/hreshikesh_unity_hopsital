package com.xworkz.hospital.restcontroller;

import com.xworkz.hospital.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/")
public class UserRestController {
@Autowired
    UserService userService;

    @GetMapping("checkUserEmail")
    public String checkUserEmail(@RequestParam String email){
        boolean status=userService.checkEmail(email);
        if(status){
            return "success";
        }else{
            return "failure";
        }

    }



    @GetMapping("/verifyOtp")
    public String verifyOtp(@RequestParam String otp, HttpSession session){
        boolean status=userService.verifyOtp(otp,(String) session.getAttribute("userEmail"));
        if(status) return"pass";
        else return "fail";
    }

}

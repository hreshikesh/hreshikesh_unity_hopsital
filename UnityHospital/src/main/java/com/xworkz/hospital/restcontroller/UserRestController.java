package com.xworkz.hospital.restcontroller;

import com.xworkz.hospital.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("checkUserMobileNumber")
    public String CheckMobileNumber(@RequestParam("phone") String userPhone){
        long convertedPhoneNo=Long.parseLong(userPhone);
        boolean status=userService.checkMobileNumber(convertedPhoneNo);
        if(status){
            return "success";
        }else{
            return "failure";
        }
    }

}

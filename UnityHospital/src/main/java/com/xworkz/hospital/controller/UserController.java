package com.xworkz.hospital.controller;

import com.xworkz.hospital.dto.UserDto;
import com.xworkz.hospital.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("registerUser")
    public ModelAndView registerUser(@Valid UserDto userDto, BindingResult result,ModelAndView view) {
        if (result.hasErrors()) {
            view.setViewName("UserSignUP");
            view.addObject("dto", userDto);
            view.addObject("error", result.getAllErrors());
            return view;
        }else{

        boolean isSaved=userService.saveUser(userDto);
        if(isSaved){
            view.setViewName("UserSignIn");
            return view;
        }else{
            view.setViewName("UserSignUP");
            view.addObject("dto", userDto);
            view.addObject("result","User Details Not Saved");
            return view;
        }
        }
    }

    @PostMapping("sendOtp")
    public String verifyAndSendOtp(@RequestParam String email, Model model){
        if(email==null){
            model.addAttribute("emailerror","Enter email");
            model.addAttribute("email",email);
        }else{
            String result=userService.verifyAndSendOtp(email);
            if(result.equals("Email Not Found")){
                model.addAttribute("emailerror","Email Not Found");
                model.addAttribute("email",email);
            }else if(result.equals("sentOtp")){
                model.addAttribute("check",true);
                model.addAttribute("otpSent",true);
                model.addAttribute("email",email);
            }
        }
        return "UserSignIn";
    }
}

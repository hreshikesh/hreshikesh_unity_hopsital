package com.xworkz.hospital.controller;

import com.xworkz.hospital.dto.UserDto;
import com.xworkz.hospital.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
            view.setViewName("UserSignUP");
        boolean isSaved=userService.saveUser(userDto);
        if(isSaved){
            view.addObject("result","User Details Successfully Saved");
        }else{
            view.addObject("dto", userDto);
            view.addObject("result","User Details Not Saved");
        }
        }
        return view;
    }
}

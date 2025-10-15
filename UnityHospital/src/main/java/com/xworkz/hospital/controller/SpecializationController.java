package com.xworkz.hospital.controller;

import com.xworkz.hospital.dto.SpecializationDto;
import com.xworkz.hospital.service.SpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class SpecializationController {
    @Autowired
    SpecializationService specializationService;

    @RequestMapping("addSpecialization")
    public ModelAndView saveSpecialiation(@Valid SpecializationDto dto, BindingResult result,ModelAndView view){
            if(result.hasErrors()){
                view.setViewName("Specialization");
                view.addObject("dto",dto);
                view.addObject("error",result.getAllErrors());
                return view;
            }
            else{
                   boolean check= specializationService.saveSpecialization(dto);
                   view.setViewName("Specialization");
                   if(check){
                       view.addObject("result","Specialization Added Successfully");
                   }else{
                       view.addObject("result","Specialization Failed to Add");
                   }
                   return view;
            }

    }

}

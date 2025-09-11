package com.xworkz.hospital.controller;

import com.xworkz.hospital.dto.DoctorDto;
import com.xworkz.hospital.entity.DoctorEntity;
import com.xworkz.hospital.service.HospitalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static sun.security.ssl.SSLLogger.info;

@Slf4j
@Controller
@RequestMapping("/")
public class HopsitalController {

    @Autowired
    HospitalService hospitalService;

    @RequestMapping("adminEmail")
    public String verifyOtpAndLogin(Model model, HttpSession session) {
        model.addAttribute("email",(String) session.getAttribute("adminEmail1"));
        return "Home";
    }


    @RequestMapping("registerDoctor")
    public ModelAndView registerDoctor(@Valid DoctorDto dto, BindingResult result,ModelAndView view){
        if(result.hasErrors()){
            view.setViewName("Doctor");
            view.addObject("dto",dto);
            view.addObject("error",result.getAllErrors());
        }else{
            boolean status=hospitalService.saveDoctor(dto);
            view.setViewName("Doctor");
            view.addObject("dto",dto);
            if(status){
                view.addObject("status","Registered SuccessFully");
                view.addObject("check",true);
            }else {
                view.addObject("status","Doctor Not Registered");
            }
        }
        return view;
    }


    @RequestMapping("searchDoctor")
    public String searchName(String name,Model model){
       DoctorDto dto= hospitalService.searchByName(name);
       log.info(dto.getDoctorName());
       if(dto.getDoctorName()==null){
           model.addAttribute("result","Doctor not found");
       }else{
           model.addAttribute("result","Doctor found");
           model.addAttribute("dto",dto);
       }
       return "Update";
    }

    @RequestMapping("updateDoctor")
    public ModelAndView updateDoctor(@Valid DoctorDto dto,BindingResult result,ModelAndView view){
        if(result.hasErrors()){
            view.setViewName("Doctor");
            view.addObject("dto",dto);
            view.addObject("error",result.getAllErrors());
        }
    }
}

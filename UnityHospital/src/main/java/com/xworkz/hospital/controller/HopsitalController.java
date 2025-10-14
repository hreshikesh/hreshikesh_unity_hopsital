package com.xworkz.hospital.controller;
import com.xworkz.hospital.dto.*;
import com.xworkz.hospital.entity.DoctorEntity;
import com.xworkz.hospital.service.DoctorService;
import com.xworkz.hospital.service.HospitalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/")
public class HopsitalController {

    @Autowired
    HospitalService hospitalService;

    @Autowired
    DoctorService doctorService;


    @RequestMapping("admin")
    public String goTOAdmin(){
        return "Admin";
    }

    @RequestMapping("index")
    public String goTOIndex(){
        return "index";
    }

    @RequestMapping("service")
    public String goToService(){return "Service";}

    @RequestMapping("Home")
    public String goToHome(HttpSession session)
    {
        session.removeAttribute("selectedSpecialization");
        session.removeAttribute("selectedDoctorName");
        session.removeAttribute("selectedDoctorId");
        return "Home";

    }

    @RequestMapping("doctor")
    public String goToDoctor(Model model){
        List<SpecializationDto> specializationDto= doctorService.getAllSpecialization();
        System.out.println(specializationDto);
        model.addAttribute("specializations",specializationDto);
        return "Doctor";
    }

    @RequestMapping("logout")
    public String logOut(HttpSession session){
        session.invalidate();
        return "index";
    }

    @RequestMapping("home")
    public String verifyOtpAndLogin(Model model, HttpSession session) {
        model.addAttribute("email",(String) session.getAttribute("adminEmail1"));
        session.setAttribute("adminLoggedIn",true);
        return "Home";
    }

    @RequestMapping("slot")
    public String goToSlot(Model model){
        List<SpecializationDto> specializationDto= doctorService.getAllSpecialization();
        model.addAttribute("specializations",specializationDto);
        return "Slot";
    }

    @RequestMapping("setSlot")
    public String goToSetSlot(Model model){
        List<SpecializationDto> specializationDto= doctorService.getAllSpecialization();
        model.addAttribute("specializations",specializationDto);
        return "SetSlot";
    }


    @RequestMapping("appointment")
    public String gotoAppointment(Model model){
        List<SpecializationDto> specializationDto= doctorService.getAllSpecialization();
        model.addAttribute("specializations",specializationDto);
        return "Appointment";
    }

    @RequestMapping("toappointment")
    public String backToAppointment(Model model,HttpSession session){
        model.addAttribute("selectedSpecialization",session.getAttribute("specialization"));
        model.addAttribute("selectedDoctorName",session.getAttribute("doctorName"));
        model.addAttribute("selectedDoctorId",session.getAttribute("doctorId"));
        List<SpecializationDto> specializationDto= doctorService.getAllSpecialization();
        model.addAttribute("specializations",specializationDto);
        return "Appointment";
    }


    @RequestMapping("specialization")
    public String gotoSpecialization(){
        return "Specialization";
    }



}

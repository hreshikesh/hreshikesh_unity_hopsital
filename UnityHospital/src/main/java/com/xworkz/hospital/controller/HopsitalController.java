package com.xworkz.hospital.controller;
import com.xworkz.hospital.dto.*;
import com.xworkz.hospital.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/")
public class HopsitalController {



    @Autowired
    PatientService patientService;

    @Autowired
    EventService eventService;

    @Autowired
    SpecializationService specializationService;


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
        return "Home";

    }

    @RequestMapping("doctor")
    public String goToDoctor(Model model){
        List<SpecializationDto> specializationDto= specializationService.getAllSpecialization();
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
        List<SpecializationDto> specializationDto= specializationService.getAllSpecialization();
        model.addAttribute("specializations",specializationDto);
        return "Slot";
    }

    @RequestMapping("setSlot")
    public String goToSetSlot(Model model){
        List<SpecializationDto> specializationDto= specializationService.getAllSpecialization();
        model.addAttribute("specializations",specializationDto);
        return "SetSlot";
    }


    @RequestMapping("appointment")
    public String gotoAppointment(Model model){
        List<SpecializationDto> specializationDto= specializationService.getAllSpecialization();
        model.addAttribute("specializations",specializationDto);
        return "Appointment";
    }

    @RequestMapping("toappointment")
    public String backToAppointment(Model model,HttpSession session){
        List<SpecializationDto> specializationDto= specializationService.getAllSpecialization();
        model.addAttribute("specializations",specializationDto);
        return "Appointment";
    }


    @RequestMapping("specialization")
    public String gotoSpecialization(){
        return "Specialization";
    }


    @RequestMapping("userSignUp")
    public String gotoUserSignUp(){
        return "UserSignUP";
    }

    @RequestMapping("signin")
    public String gotoSignIn(){
        return "UserSignIn";
    }

    @RequestMapping("userDashboard")
    public String gotoUserDashboard(Model model,HttpSession session){
        model.addAttribute("user",session.getAttribute("userName"));
        List<EventDto> eventDtos = eventService.getAllEvent();
        model.addAttribute("dtos", eventDtos);
        if (eventDtos == null || eventDtos.isEmpty()) {
            eventDtos.addAll(Arrays.asList(
                    new EventDto(0, "Welcome to Unity Hospital - Excellence in Healthcare", null),
                    new EventDto(0, "24/7 Emergency Services - Call +91-12345-67890 ", null),
                    new EventDto(0, "Book OPD Appointments Online - Quick & Easy", null),
                    new EventDto(0, "Get Affordable Health Checkup Packages Today", null),
                    new EventDto(0, "Unity Hospital - Caring Beyond Limits", null)
            ));
            model.addAttribute("dtos", eventDtos);
        }
        return "UserDashBoard";
    }

    @GetMapping("myAppointment")
    public String gotoMyAppointment(){
        return  "CheckAppointment";
    }

    @GetMapping("patientRegistration")
    public String gotoUserPatientRegistration(Model model){
        List<SpecializationDto> specializationDtos=specializationService.getAllSpecialization();
        List<BloodGroupDto> dtos= patientService.getAllBloodGroup();
        model.addAttribute("specializationDtos",specializationDtos);
        model.addAttribute("bloodGroupDtos",dtos);
        return "UserPatientRegistration";
    }

    @GetMapping("event")
    public String gotoEventPage(){
        return "Event";
    }


    @GetMapping("modifyEvent")
    public String gotoModifyEvent(Model model){
        List<EventDto> eventDtos=eventService.getAllEvent();
        log.info(eventDtos.toString());
        if(eventDtos!=null&&!eventDtos.isEmpty()) {
            model.addAttribute("check",true);
            model.addAttribute("dtos", eventDtos);
        }else {
            model.addAttribute("check", false);
        }
        return "ModifyEvent";
    }

@GetMapping("modifySpecialization")
    public String gotoModifySpecialization(Model model){
    List<SpecializationDto> specializationDtos=specializationService.getAllSpecialization();
    log.info(specializationDtos.toString());
    if(specializationDtos!=null&&!specializationDtos.isEmpty()) {
        model.addAttribute("check",true);
        model.addAttribute("dtos", specializationDtos);
    }else {
        model.addAttribute("check", false);
    }
    return "ModifySpecialization";
}


}

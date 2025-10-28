package com.xworkz.hospital.controller;


import com.xworkz.hospital.dto.PatientDto;
import com.xworkz.hospital.dto.SpecializationDto;
import com.xworkz.hospital.service.DoctorService;
import com.xworkz.hospital.service.PatientService;
import com.xworkz.hospital.service.SpecializationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/")
public class AppointmentController {
    @Autowired
    DoctorService doctorService;

    @Autowired
    PatientService patientService;

    @Autowired
    SpecializationService specializationService;

    @RequestMapping("getAppointments")
    public String  getAppointment(@RequestParam(defaultValue = "0") int doctorId, @RequestParam(defaultValue = "0") int slot,Model model){
            log.info(String.valueOf(doctorId));
            log.info(String.valueOf(slot));
        if(doctorId==0){
            List<SpecializationDto> specializations=specializationService.getAllSpecialization();
            model.addAttribute("specializations",specializations);
            model.addAttribute("result","Select Doctor to check appointments");
            return "Appointment";
        }else  if(slot==0) {
            List<SpecializationDto> specializations = specializationService.getAllSpecialization();
            model.addAttribute("specializations", specializations);
            model.addAttribute("result", "Select Slot to get patient details");
            return "Appointment";
        }else{
            log.info(String.valueOf(doctorId));
            List<SpecializationDto> specializations = specializationService.getAllSpecialization();
            model.addAttribute("specializations", specializations);
            List<PatientDto> patientDtos = patientService.getPatient(doctorId,slot);
            if (patientDtos == null || patientDtos.isEmpty()) {
                model.addAttribute("result", "No Appointments for today");
                return "Appointment";
            } else {
                model.addAttribute("dtos", patientDtos);
                return "Appointment";
            }
        }
    }

    @RequestMapping("details")
    public String getPatientDetails(@RequestParam int patientId,Model model){
        log.info(String.valueOf(patientId));
        PatientDto patientDto=  patientService.getPatientDetails(patientId);
        if(patientDto==null){
            model.addAttribute("result","Patient Details cannot be found");
            return "Appointment";
        }else{
            model.addAttribute("dto",patientDto);
            return "Details";
        }
    }


    @GetMapping("searchUser")
    public ModelAndView getUserDetails(String regid,ModelAndView modelAndView){
        if(regid==null){
            modelAndView.setViewName("CheckAppointment");
            modelAndView.addObject("regId",regid);
            modelAndView.addObject("error","Registration Id Cannot be Empty");
            return modelAndView;
        }else{
            modelAndView.setViewName("CheckAppointment");
            if(regid.matches( "^UNITY[a-zA-Z]{2}-\\d{2}(0[1-9]|1[0-2])-\\d{4}$")){
                modelAndView.addObject("error","Invalid registration Id");
                return modelAndView;
            }else{
                PatientDto patientDto=patientService.findPatientByRegistrationId(regid);
                if(patientDto==null){
                    modelAndView.addObject("result","No Patient Found for Registration Id");
                    return modelAndView;
                }
                modelAndView.addObject("dto",patientDto);
                return modelAndView;
            }
        }
    }



}

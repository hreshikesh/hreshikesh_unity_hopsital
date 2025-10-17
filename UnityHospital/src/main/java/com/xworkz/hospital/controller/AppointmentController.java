package com.xworkz.hospital.controller;


import com.xworkz.hospital.dto.PatientDto;
import com.xworkz.hospital.dto.SpecializationDto;
import com.xworkz.hospital.service.DoctorService;
import com.xworkz.hospital.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/")
public class AppointmentController {
    @Autowired
    DoctorService doctorService;

    @Autowired
    PatientService patientService;

    @RequestMapping("getAppointments")
    public String  getAppointment(String  specialization, String doctorName, @RequestParam(defaultValue = "0") int doctorId, @RequestParam(defaultValue = "0") int slot,Model model, HttpSession session){

        if(doctorId==0){
            List<SpecializationDto> specializations=doctorService.getAllSpecialization();
            model.addAttribute("specializations",specializations);
            model.addAttribute("result","Select Doctor to check appointments");
            return "Appointment";
        }else  if(slot==0) {
            List<SpecializationDto> specializations = doctorService.getAllSpecialization();
            model.addAttribute("specializations", specializations);
            model.addAttribute("result", "Select Slot to get patient details");
            return "Appointment";
        }else{
            log.info(String.valueOf(doctorId));
            List<SpecializationDto> specializations = doctorService.getAllSpecialization();
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



}

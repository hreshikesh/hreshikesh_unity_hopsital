package com.xworkz.hospital.controller;

import com.xworkz.hospital.dto.BloodGroupDto;
import com.xworkz.hospital.dto.SpecializationDto;
import com.xworkz.hospital.service.HospitalService;
import com.xworkz.hospital.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/")
public class PatientController {
    @Autowired
    HospitalService hospitalService;
    @Autowired
    PatientService patientService;

    @GetMapping("patient")
    public  String goToPatientRegistration(Model model){
        List<SpecializationDto> specializationDtos=hospitalService.getAllSpecialization();
       List<BloodGroupDto> dtos= patientService.getAllBloodGroup();
       log.info(dtos.toString());
        model.addAttribute("specializationDtos",specializationDtos);
        model.addAttribute("bloodGroupDtos",dtos);
        return "PatientRegistration";
    }


}

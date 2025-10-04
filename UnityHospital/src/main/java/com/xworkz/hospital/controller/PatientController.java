package com.xworkz.hospital.controller;

import com.xworkz.hospital.dto.BloodGroupDto;
import com.xworkz.hospital.dto.PatientDto;
import com.xworkz.hospital.dto.SpecializationDto;
import com.xworkz.hospital.service.DoctorService;
import com.xworkz.hospital.service.HospitalService;
import com.xworkz.hospital.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/")
public class PatientController {
    @Autowired
    HospitalService hospitalService;
    @Autowired
    PatientService patientService;

    @Autowired
    DoctorService doctorService;

    @GetMapping("patient")
    public  String goToPatientRegistration(Model model){
        List<SpecializationDto> specializationDtos=doctorService.getAllSpecialization();
       List<BloodGroupDto> dtos= patientService.getAllBloodGroup();
       log.info(dtos.toString());
        model.addAttribute("specializationDtos",specializationDtos);
        model.addAttribute("bloodGroupDtos",dtos);
        return "PatientRegistration";
    }

    @RequestMapping("registerPatient")
    public ModelAndView registerPatient(@Valid PatientDto dto, BindingResult result,ModelAndView view){
        view.setViewName("PatientRegistration");
        List<SpecializationDto> specializationDtos=doctorService.getAllSpecialization();
        List<BloodGroupDto> dtos= patientService.getAllBloodGroup();
        view.addObject("bloodGroupDtos",dtos);
        view.addObject("specializationDtos",specializationDtos);
        if(result.hasErrors()){
            view.addObject("error",result.getAllErrors());
            view.addObject("dto",dto);
        }else{
                if(dto!=null ){
                    StringBuilder sb=new StringBuilder();
                    sb.append("unity");
                    sb.append(dto.getName().substring(0,2));
                    sb.append(String.valueOf(dto.getPhone()).substring(0,2));
                    sb.append(dto.getSpecialization().substring(0,2));
                    dto.setRegistrationId(sb.toString().toUpperCase());
                    log.info(dto.getRegistrationId());
                    boolean check=patientService.savePatientDetails(dto);
                    if(check){
                        view.addObject("result","Successfully saved Patient Details");
                    }else{
                        view.addObject("result","Patient Details not saved");
                    }
                }

        }
        return view;
    }


}

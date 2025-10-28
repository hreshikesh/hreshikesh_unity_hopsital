package com.xworkz.hospital.controller;

import com.xworkz.hospital.dto.BloodGroupDto;
import com.xworkz.hospital.dto.PatientDto;
import com.xworkz.hospital.dto.SpecializationDto;
import com.xworkz.hospital.service.DoctorService;
import com.xworkz.hospital.service.HospitalService;
import com.xworkz.hospital.service.PatientService;
import com.xworkz.hospital.service.SpecializationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.text.DateFormatter;
import javax.validation.Valid;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Controller
@Slf4j
@RequestMapping("/")
public class PatientController {

    @Autowired
    PatientService patientService;

    @Autowired
    DoctorService doctorService;

    @Autowired
    SpecializationService specializationService;

    @GetMapping("patient")
    public  String goToPatientRegistration(Model model){
        List<SpecializationDto> specializationDtos=specializationService.getAllSpecialization();
       List<BloodGroupDto> dtos= patientService.getAllBloodGroup();
       log.info(dtos.toString());
        model.addAttribute("specializationDtos",specializationDtos);
        model.addAttribute("bloodGroupDtos",dtos);
        return "PatientRegistration";
    }

    @GetMapping("preview")
    public void preview(HttpServletResponse response, @RequestParam String imagePath)throws IOException {
        response.setContentType("image/jpeg");
        File file = new File("D:\\pateintProfile\\" + imagePath);
        if (file.exists()) {
            InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
            ServletOutputStream outputStream = response.getOutputStream();
            IOUtils.copy(inputStream, outputStream);
            response.flushBuffer();
        }
    }

    @RequestMapping("registerPatient")
    public ModelAndView registerPatient(@Valid PatientDto dto, BindingResult result,ModelAndView view) throws IOException {
        view.setViewName("PatientRegistration");
        List<SpecializationDto> specializationDtos=specializationService.getAllSpecialization();
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
                    sb.append("-");
                    sb.append(new SimpleDateFormat("yyMM").format(new Date()));
                    sb.append("-");
                    sb.append(String.valueOf(new Random().nextInt(9000)+1000));
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

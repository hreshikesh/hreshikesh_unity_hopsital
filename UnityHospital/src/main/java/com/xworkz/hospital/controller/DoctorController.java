package com.xworkz.hospital.controller;

import com.xworkz.hospital.dto.DoctorDto;
import com.xworkz.hospital.dto.SpecializationDto;
import com.xworkz.hospital.service.DoctorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/")
@Tag(name = "Doctor Controller",description = "This controller is for doctor in unity hospital")
public class DoctorController {
    @Autowired
    DoctorService doctorService;

    @PostMapping("registerDoctor")
    @Operation(summary = "Register a doctor",description = "creates a new record in the doctor table")
    public ModelAndView registerDoctor(@Valid DoctorDto dto, BindingResult result, ModelAndView view) throws IOException {
        List<SpecializationDto> specializationDto= doctorService.getAllSpecialization();
        view.addObject("specializations",specializationDto);
        if(result.hasErrors()){
            view.setViewName("Doctor");
            view.addObject("dto",dto);
            view.addObject("error",result.getAllErrors());
            return view;
        }
        if (dto.getImage().getSize()>1*1024*1024) {
            view.setViewName("Doctor");
            view.addObject("dto",dto);
            view.addObject("imageError", "Image Size Cannot exceed 1MB");
            return view;
        }
        boolean status=doctorService.saveDoctor(dto);
        view.setViewName("Doctor");
        if(status){
            view.addObject("status","Registered SuccessFully");
        }else {
            view.addObject("dto",dto);
            view.addObject("status","Doctor Not Registered");
        }
        return view;

    }



    @RequestMapping("updateClick")
    @Operation(summary = "Loads the update page")
    public String updateRedirect(String email, Model model){
        DoctorDto dto= doctorService.searchByEmail(email);
        if(dto==null ){
            model.addAttribute("result","Doctor not found");
        }else{

            model.addAttribute("dto",dto);
            List<SpecializationDto> specializationDto= doctorService.getAllSpecialization();
            model.addAttribute("specializations",specializationDto);
        }
        return "Update";
    }

    @RequestMapping("updateDoctor")
    @Operation(summary = "Update  doctor details",description = "updates the existing doctor details in the  db")
    public ModelAndView updateDoctor(@Valid DoctorDto dto,BindingResult result,ModelAndView view) throws IOException {

        List<SpecializationDto> specializationDto = doctorService.getAllSpecialization();
        view.addObject("specializations", specializationDto);

        if (dto.getImage() == null || dto.getImage().isEmpty()) {
            DoctorDto existingDto = doctorService.searchByEmail(dto.getDoctorEmail());
            if (existingDto != null && existingDto.getImagePath() != null) {
                dto.setImagePath(existingDto.getImagePath());
            }
        }


        if (result.hasErrors()) {
            view.setViewName("Update");
            view.addObject("dto", dto);
            view.addObject("error", result.getAllErrors());
            return view;
        }

        if (dto.getImage().getSize()>1*1024*1024) {
            view.setViewName("Update");
            DoctorDto existingDto = doctorService.searchByEmail(dto.getDoctorEmail());
            if (existingDto != null && existingDto.getImagePath() != null) {
                dto.setImagePath(existingDto.getImagePath());
            }
            view.addObject("dto",dto);
            view.addObject("imageError", "Image Size Cannot exceed 1MB");
            return view;
        }

        boolean status = doctorService.updateDoctor(dto);

        DoctorDto updatedDto = doctorService.searchByEmail(dto.getDoctorEmail());
        if (updatedDto != null && updatedDto.getImagePath() != null) {
            dto.setImagePath(updatedDto.getImagePath());
        }

        view.setViewName("Update");
        view.addObject("dto", dto);

        if (status) {
            view.addObject("status", "Updated Successfully");
        } else {
            view.addObject("status", "Doctor Details Not Updated");
        }
        return view;
    }

    @GetMapping("download")
    @Operation(summary = "Image preview",description = "previews the image")
    public void download(HttpServletResponse response, @RequestParam String imagePath)throws IOException{
        response.setContentType("image/jpeg");
        File file=new File("D:\\unity_hospital\\"+imagePath);
        if(file.exists()) {
            InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
            ServletOutputStream outputStream = response.getOutputStream();
            IOUtils.copy(inputStream, outputStream);
            response.flushBuffer();
        }
    }

    @GetMapping("alldoctor")
    @Operation(summary = "Get all doctor details",description = "Fetching all doctor details from db")
    public ModelAndView getAllDoctors(ModelAndView modelAndView){

        List<DoctorDto> list=doctorService.getAllDoctor();
        modelAndView.setViewName("AllDoctor");
        if (list.isEmpty()){
            modelAndView.addObject("status","No Doctors are found");
        }else {
            modelAndView.addObject("dtolist",list);
        }
        return modelAndView;
    }



    @RequestMapping("deleteDoctor")
    @Operation(summary = "delete a doctor",description = "deletea  record in the doctor table")
    public String deleteDoctorDetails(String email,Model model){
        boolean check=doctorService.deleteDoctor(email);


        if(!check) {
            model.addAttribute("deleteMessage","Doctor not Deleted Successfully");
        }else {
            model.addAttribute("deleteMessage", "Doctor Deleted Successfully");
        }
        List<DoctorDto> doctorList = doctorService.getAllDoctor();
        model.addAttribute("dtolist", doctorList);
        return "AllDoctor";
    }


}

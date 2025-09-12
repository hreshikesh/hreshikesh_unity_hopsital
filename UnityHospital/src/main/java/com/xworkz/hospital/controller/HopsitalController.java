package com.xworkz.hospital.controller;

import com.xworkz.hospital.dto.DoctorDto;
import com.xworkz.hospital.service.HospitalService;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

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


    private String fileUpload(String name,MultipartFile file) throws IOException {
        byte[] filePart= file.getBytes();
        Path path=Paths.get("D:\\moduleImages\\"+name+System.currentTimeMillis()+".jpg");
        Files.write(path,filePart);
        return path.getFileName().toString();
    }

    @PostMapping("registerDoctor")
    public ModelAndView registerDoctor(@RequestParam("image")MultipartFile file, @Valid DoctorDto dto, BindingResult result, ModelAndView view) throws IOException {
        if(result.hasErrors()){
            view.setViewName("Doctor");
            view.addObject("dto",dto);
            view.addObject("error",result.getAllErrors());
        }else{
            String imagePath=fileUpload(dto.getDoctorName(),file);
            dto.setImagePath(imagePath);
            boolean status=hospitalService.saveDoctor(dto);
            view.setViewName("Doctor");
            if(status){
                view.addObject("dto",dto);
                view.addObject("status","Registered SuccessFully");
            }else {
                view.addObject("status","Doctor Not Registered");
            }
        }
        return view;
    }


    @RequestMapping("searchDoctor")
    public String searchName(String email,Model model){
       DoctorDto dto= hospitalService.searchByEmail(email);
       log.info(dto.getDoctorName());
       if(dto.getDoctorEmail()==null){
           model.addAttribute("result","Doctor not found");
       }else{
           model.addAttribute("result","Doctor found");
           model.addAttribute("dto",dto);
       }
       return "Update";
    }

    @RequestMapping("updateDoctor")
    public ModelAndView updateDoctor(@RequestParam("image")MultipartFile file,@Valid DoctorDto dto,BindingResult result,ModelAndView view) throws IOException {
        if(result.hasErrors()){
            view.setViewName("Update");
            view.addObject("dto",dto);
            view.addObject("error",result.getAllErrors());
        }else{
            String imagePath=fileUpload(dto.getDoctorName(),file);
            dto.setImagePath(imagePath);
           boolean status= hospitalService.updateDoctor(dto);
            view.setViewName("Update");
            view.addObject("dto",dto);
            if(status){
                view.addObject("status","Updated SuccessFully");
            }else {
                view.addObject("status","Doctor Details Not Updated");
            }
        }
        return view;
    }

    @GetMapping("download")
    public void download(HttpServletResponse response,@RequestParam String imagePath)throws IOException{
        response.setContentType("image/jpeg");
        File file=new File("D:\\moduleImages\\"+imagePath);
        InputStream inputStream=new BufferedInputStream(new FileInputStream(file));
        ServletOutputStream outputStream= response.getOutputStream();
        IOUtils.copy(inputStream,outputStream);
        response.flushBuffer();
    }

    @GetMapping("alldoctor")
    public ModelAndView getAllDoctors(ModelAndView modelAndView){

        List<DoctorDto> list=hospitalService.getAllDoctor();
        modelAndView.setViewName("AllDoctor");
        if (list.isEmpty()){
            modelAndView.addObject("status","No Doctors are found");
        }else {
            modelAndView.addObject("dtolist",list);
        }
        return modelAndView;
    }

}

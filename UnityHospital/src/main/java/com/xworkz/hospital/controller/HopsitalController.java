package com.xworkz.hospital.controller;
import com.xworkz.hospital.dto.*;
import com.xworkz.hospital.entity.DoctorEntity;
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


    @RequestMapping("admin")
    public String goTOAdmin(){
        return "admin";
    }

    @RequestMapping("index")
    public String goTOIndex(){
        return "index";
    }

    @RequestMapping("service")
    public String goToService(){return "Service";}

    @RequestMapping("Home")
    public String goToHome(){
        return "Home";
    }

    @RequestMapping("doctor")
    public String goToDoctor(Model model){
        List<SpecializationDto> specializationDto= hospitalService.getAllSpecialization();
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




    @PostMapping("registerDoctor")
    public ModelAndView registerDoctor(@Valid DoctorDto dto, BindingResult result, ModelAndView view) throws IOException {
        List<SpecializationDto> specializationDto= hospitalService.getAllSpecialization();
        view.addObject("specializations",specializationDto);
        if(result.hasErrors()){
            view.setViewName("Doctor");
            view.addObject("dto",dto);
            view.addObject("error",result.getAllErrors());
        }else{

            boolean status=hospitalService.saveDoctor(dto);
            view.setViewName("Doctor");
            if(status){
                view.addObject("status","Registered SuccessFully");
            }else {
                view.addObject("dto",dto);
                view.addObject("status","Doctor Not Registered");
            }
        }
        return view;
    }



    @RequestMapping("updateClick")
    public String updateRedirect(String email,Model model){
        DoctorDto dto= hospitalService.searchByEmail(email);
        if(dto==null ){
            model.addAttribute("result","Doctor not found");
        }else{

            model.addAttribute("dto",dto);
            List<SpecializationDto> specializationDto= hospitalService.getAllSpecialization();
            model.addAttribute("specializations",specializationDto);
        }
        return "Update";
    }

    @RequestMapping("updateDoctor")
    public ModelAndView updateDoctor(@Valid DoctorDto dto,BindingResult result,ModelAndView view) throws IOException {

        List<SpecializationDto> specializationDto = hospitalService.getAllSpecialization();
        view.addObject("specializations", specializationDto);

        if (dto.getImage() == null || dto.getImage().isEmpty()) {
            DoctorDto existingDto = hospitalService.searchByEmail(dto.getDoctorEmail());
            if (existingDto != null && existingDto.getImagePath() != null) {
                dto.setImagePath(existingDto.getImagePath());
            }
        }


        if (result.hasErrors()) {
            view.setViewName("Update");
            view.addObject("dto", dto);
            view.addObject("error", result.getAllErrors());
        } else {

            boolean status = hospitalService.updateDoctor(dto);

            DoctorDto updatedDto = hospitalService.searchByEmail(dto.getDoctorEmail());
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
        }

        return view;
    }

    @GetMapping("download")
    public void download(HttpServletResponse response,@RequestParam String imagePath)throws IOException{
        response.setContentType("image/jpeg");
        File file=new File("D:\\unity_hospital\\"+imagePath);
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


    @RequestMapping("slot")
    public String goToSlot(Model model){
        List<SpecializationDto> specializationDto= hospitalService.getAllSpecialization();
        model.addAttribute("specializations",specializationDto);
        return "Slot";
    }

    @RequestMapping("setSlot")
    public String goToSetSlot(Model model){
        List<SpecializationDto> specializationDto= hospitalService.getAllSpecialization();
        model.addAttribute("specializations",specializationDto);
        return "SetSlot";
    }


    @RequestMapping("settimeslot")
    public ModelAndView setTimeSlot(@Valid TimeSlotDto dto,BindingResult result,ModelAndView view){
        List<SpecializationDto> specializationDto= hospitalService.getAllSpecialization();
        view.addObject("specializations",specializationDto);
        if(result.hasErrors()){
            view.addObject("errors",result.getAllErrors());
            view.setViewName("SetSlot");
        }else{
           LocalTime endTime=LocalTime.parse(dto.getToTime());
           LocalTime startTime=LocalTime.parse(dto.getFromTime());
           DateTimeFormatter formatter=DateTimeFormatter.ofPattern("hh:mm a");
           dto.setToTime(endTime.format(formatter));
           dto.setFromTime(startTime.format(formatter));

          boolean check=hospitalService.saveTimeInterval(dto);
          if(check){
              view.addObject("result","Time has been set");
              view.setViewName("SetSlot");
          }else {
              view.addObject("result","Time not set");
          }
        }
        return view;
    }




    @RequestMapping("doctorspecialization")
    public ModelAndView findDoctorWithSpecialization(String specialization,ModelAndView modelAndView){
        List<DoctorEntity>  doctors= hospitalService.findDoctorBySpecialization(specialization);
      List<TimeSlotDto> timeSlotDtos= hospitalService.findAllIntervals(specialization);
        modelAndView.addObject("check",false);
      List<String> timeIntervals=new ArrayList<>();
      List<DoctorDto> dtos=hospitalService.getAllDoctor();
        List<SpecializationDto> specializationDto= hospitalService.getAllSpecialization();
        modelAndView.addObject("specializations",specializationDto);
       if(doctors.isEmpty()){
           modelAndView.addObject("dtos",dtos);
           modelAndView.addObject("result","No Doctors found for "+specialization);
           modelAndView.setViewName("Slot");
       }else {
           modelAndView.addObject("specializationEntered",specialization);
           modelAndView.addObject("check",true);
           modelAndView.addObject("doctordto",doctors);
           if(timeSlotDtos==null||timeSlotDtos.isEmpty()){
               modelAndView.addObject("result","No Time Slots set  Please Set the TIme Slots");
           }else{
               for(TimeSlotDto timeSlotDto:timeSlotDtos){
                   String time=timeSlotDto.getFromTime()+"-"+timeSlotDto.getToTime();
                   timeIntervals.add(time);
               }
               modelAndView.addObject("timeIntervals",timeIntervals);
           }
           modelAndView.setViewName("Slot");
       }
       return modelAndView;
    }



    @RequestMapping("doctorSave")
    public String setDoctorSlot(@RequestParam String specialization,@Valid DoctorTimeSlotDto dto,BindingResult result,Model model){
        List<SpecializationDto> specializationDto = hospitalService.getAllSpecialization();
        if(result.hasErrors()){
            model.addAttribute("error",result.getAllErrors());
            model.addAttribute("specializations", specializationDto);
            model.addAttribute("check",true);
            model.addAttribute("specializationEntered",specialization);
            model.addAttribute("dtos",dto);
        }
        else {
            String isSet = hospitalService.setTimeSlot(dto);
            model.addAttribute("specializations", specializationDto);
            if (isSet.equals("saveSuccess")) {
                model.addAttribute("update", "Slot has been set");
            } else if(isSet.equals("saveFail") || isSet==null){
                model.addAttribute("update", "Slot has not been set");
                model.addAttribute("check",true);
                model.addAttribute("specializationEntered",specialization);
                model.addAttribute("dtos",dto);
            }
        }
    return "Slot";
    }


    @RequestMapping("deleteDoctor")
    public String deleteDoctorDetails(String email,Model model){
        boolean check=hospitalService.deleteDoctor(email);


        if(!check) {
            model.addAttribute("deleteMessage","Doctor not Deleted Successfully");
        }else {
            model.addAttribute("deleteMessage", "Doctor Deleted Successfully");
        }
        List<DoctorDto> doctorList = hospitalService.getAllDoctor();
        model.addAttribute("dtolist", doctorList);
        return "AllDoctor";
    }
}

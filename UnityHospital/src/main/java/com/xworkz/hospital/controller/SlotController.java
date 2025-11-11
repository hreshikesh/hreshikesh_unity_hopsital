package com.xworkz.hospital.controller;

import com.xworkz.hospital.dto.DoctorDto;
import com.xworkz.hospital.dto.DoctorTimeSlotDto;
import com.xworkz.hospital.dto.SpecializationDto;
import com.xworkz.hospital.dto.TimeSlotDto;
import com.xworkz.hospital.entity.DoctorEntity;
import com.xworkz.hospital.service.DoctorService;
import com.xworkz.hospital.service.SlotService;
import com.xworkz.hospital.service.SpecializationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/")
public class SlotController {
    @Autowired
    DoctorService doctorService;

    @Autowired
    SlotService slotService;

    @Autowired
    SpecializationService specializationService;


    @RequestMapping("settimeslot")
    public ModelAndView setTimeSlot(@Valid TimeSlotDto dto, BindingResult result, ModelAndView view){
        List<SpecializationDto> specializationDto= specializationService.getAllSpecialization();
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

            boolean check=slotService.saveTimeInterval(dto);
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
        List<DoctorEntity>  doctors= slotService.findDoctorBySpecialization(specialization);
        List<TimeSlotDto> timeSlotDtos= slotService.findAllIntervals(specialization);
        modelAndView.addObject("check",false);
        List<String> timeIntervals=new ArrayList<>();
        List<DoctorDto> dtos=doctorService.getAllDoctor(0,0);
        List<SpecializationDto> specializationDto= specializationService.getAllSpecialization();
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
    public String setDoctorSlot(@RequestParam String specialization, @Valid DoctorTimeSlotDto dto, BindingResult result, Model model){
        List<SpecializationDto> specializationDto = specializationService.getAllSpecialization();
        if(result.hasErrors()){
            model.addAttribute("error",result.getAllErrors());
            model.addAttribute("specializations", specializationDto);
            model.addAttribute("check",true);
            model.addAttribute("specializationEntered",specialization);
            model.addAttribute("dtos",dto);
        }
        else {
            String isSet = slotService.setTimeSlot(dto);
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

}

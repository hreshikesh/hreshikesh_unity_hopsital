package com.xworkz.hospital.controller;

import com.xworkz.hospital.dto.SpecializationDto;
import com.xworkz.hospital.service.SpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/")
public class SpecializationController {
    @Autowired
    SpecializationService specializationService;

    @RequestMapping("addSpecialization")
    public ModelAndView saveSpecialiation(@Valid SpecializationDto dto, BindingResult result,ModelAndView view){
            if(result.hasErrors()){
                view.setViewName("Specialization");
                view.addObject("dto",dto);
                view.addObject("error",result.getAllErrors());
                return view;
            }
            else{
                   boolean check= specializationService.saveSpecialization(dto);
                   view.setViewName("Specialization");
                   if(check){
                       view.addObject("result","Specialization Added Successfully");
                   }else{
                       view.addObject("result","Specialization Failed to Add");
                   }
                   return view;
            }

    }

    @PostMapping("deleteSpecialization")
    public String deleteEvent(@RequestParam(defaultValue = "0") int id, Model model) {
        List<SpecializationDto> specializationDtos = specializationService.getAllSpecialization();
        if (id != 0) {
            boolean isDeleted = specializationService.deleteSpecialization(id);
            if (isDeleted) {
                List<SpecializationDto> specializationDtos1 = specializationService.getAllSpecialization();
                if (specializationDtos1 != null && !specializationDtos1.isEmpty()) {
                    model.addAttribute("check", true);
                    model.addAttribute("dtos", specializationDtos1);
                } else {
                    model.addAttribute("check", false);
                }
                model.addAttribute("result", "Successfully deleted Specialization");
            } else {
                if (specializationDtos != null && !specializationDtos.isEmpty()) {
                    model.addAttribute("check", true);
                    model.addAttribute("dtos", specializationDtos);
                } else {
                    model.addAttribute("check", false);
                }
                model.addAttribute("result", "Issue in deleting Specialization");
            }
            return "ModifySpecialization";
        } else {
            if (specializationDtos != null && !specializationDtos.isEmpty()) {
                model.addAttribute("check", true);
                model.addAttribute("dtos", specializationDtos);
            } else {
                model.addAttribute("check", false);
            }
            model.addAttribute("result", "Issue in deleting Event");
            return "ModifySpecialization";
        }
    }

}

package com.xworkz.hospital.controller;

import com.xworkz.hospital.dto.EventDto;
import com.xworkz.hospital.service.EventService;
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
public class EventController{

    @Autowired
    EventService eventService;

    @RequestMapping("addEvent")
            public ModelAndView addEvent(@Valid EventDto eventDto, BindingResult result, ModelAndView view){
        if(result.hasErrors()){
            view.setViewName("Event");
            view.addObject("dto",eventDto);
            view.addObject("error",result.getAllErrors());
            return view;
        }else{
         boolean check= eventService.save(eventDto);
            view.setViewName("Event");
            if(check){
                view.addObject("result","Event Added Successfully");
            }else{
                view.addObject("result","Event Failed to Add");
            }
            return view;
        }
    }

    @PostMapping("deleteEvent")
    public String deleteEvent(@RequestParam(defaultValue = "0") int id,Model model) {
        List<EventDto> eventDtos=eventService.getAllEvent();
        if (id != 0) {
            boolean isDeleted = eventService.deleteEvent(id);
            if (isDeleted) {
                List<EventDto> eventDtos1=eventService.getAllEvent();
                if(eventDtos1!=null && !eventDtos1.isEmpty()) {
                    model.addAttribute("check",true);
                    model.addAttribute("dtos", eventDtos1);
                }else {
                    model.addAttribute("check", false);
                }
                model.addAttribute("result", "Successfully deleted Event");
            } else {
                if(eventDtos!=null &&!eventDtos.isEmpty()) {
                    model.addAttribute("check",true);
                    model.addAttribute("dtos", eventDtos);
                }else {
                    model.addAttribute("check", false);
                }
                model.addAttribute("result", "Issue in deleting Event");
            }
            return "ModifyEvent";
        } else {
            if(eventDtos!=null &&!eventDtos.isEmpty()) {
                model.addAttribute("check",true);
                model.addAttribute("dtos", eventDtos);
            }else {
                model.addAttribute("check", false);
            }
            model.addAttribute("result", "Issue in deleting Event");
            return "ModifyEvent";
        }
    }
}


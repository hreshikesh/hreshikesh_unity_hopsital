package com.xworkz.hospital.controller;

import com.xworkz.hospital.dto.EventDto;
import com.xworkz.hospital.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

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
}


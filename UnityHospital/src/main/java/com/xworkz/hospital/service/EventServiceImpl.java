package com.xworkz.hospital.service;

import com.xworkz.hospital.dto.EventDto;
import com.xworkz.hospital.entity.EventEntity;
import com.xworkz.hospital.repository.EventRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;

@Service
public class EventServiceImpl  implements EventService{
    @Autowired
    EventRepository eventRepository;


    @Override
    public boolean save(EventDto eventDto) {
        if(eventDto!=null){
            EventEntity eventEntity=new EventEntity();
            eventEntity.setEvent(eventDto.getEvent());
            eventEntity.setDate(LocalDate.now());
            return  eventRepository.save(eventEntity);
        }
        return false;
    }
}

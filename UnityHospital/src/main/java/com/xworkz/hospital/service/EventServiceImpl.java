package com.xworkz.hospital.service;

import com.xworkz.hospital.dto.EventDto;
import com.xworkz.hospital.entity.EventEntity;
import com.xworkz.hospital.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<EventDto> getAllEvent() {
        List<EventEntity> entities = eventRepository.getAllEvent();
        List<EventDto> eventDtos = new ArrayList<>();
        if (entities != null && !entities.isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
            for (EventEntity eventEntity : entities) {
                if (eventEntity != null) {
                    EventDto eventDto = new EventDto();
                    eventDto.setId(eventEntity.getId());
                    eventDto.setEvent(eventEntity.getEvent());
                    String formattedDate = formatter.format(eventEntity.getDate());
                    eventDto.setDate(formattedDate);
                    eventDtos.add(eventDto);
                }
            }
        }
        return eventDtos;
    }

    @Override
    public boolean deleteEvent(int id) {
        return eventRepository.deleteEvent(id);
    }
}

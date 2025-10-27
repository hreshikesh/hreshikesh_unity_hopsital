package com.xworkz.hospital.service;

import com.xworkz.hospital.dto.EventDto;

import java.util.List;

public interface EventService {
    boolean save(EventDto eventDto);

    List<EventDto> getAllEvent();

    boolean deleteEvent(int id);
}

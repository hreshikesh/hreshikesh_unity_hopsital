package com.xworkz.hospital.repository;

import com.xworkz.hospital.dto.EventDto;
import com.xworkz.hospital.entity.EventEntity;

import java.util.List;

public interface EventRepository {

    boolean save(EventEntity eventEntity);

    List<EventEntity> getAllEvent();

   boolean deleteEvent(int id);

}

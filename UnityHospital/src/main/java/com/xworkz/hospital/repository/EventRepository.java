package com.xworkz.hospital.repository;

import com.xworkz.hospital.dto.EventDto;
import com.xworkz.hospital.entity.EventEntity;

public interface EventRepository {

    boolean save(EventEntity eventEntity);

}

package com.xworkz.hospital.service;

import com.xworkz.hospital.dto.DoctorTimeSlotDto;
import com.xworkz.hospital.dto.TimeSlotDto;
import com.xworkz.hospital.entity.DoctorEntity;

import java.util.List;

public interface SlotService {
    boolean saveTimeInterval(TimeSlotDto dto);

    List<DoctorEntity> findDoctorBySpecialization(String specialization);

    List<TimeSlotDto> findAllIntervals(String specialization);

    boolean checkInterval(String email, String interval);

    String setTimeSlot(DoctorTimeSlotDto dto);

    int checkIntervalForSpecification(String specialization,String timeInterval);
}

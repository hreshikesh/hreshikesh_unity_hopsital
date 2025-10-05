package com.xworkz.hospital.repository;

import com.xworkz.hospital.entity.DoctorEntity;
import com.xworkz.hospital.entity.DoctorTimeSlotEntity;
import com.xworkz.hospital.entity.TimeSlotEntity;

import java.util.List;

public interface SlotRepository {
    boolean saveTimeInterval(TimeSlotEntity entity);

    List<DoctorEntity> findDoctorBySpecialization(String specialization);

    List<TimeSlotEntity> findAllIntervals(String specialization);

    boolean setTimeSlot(DoctorTimeSlotEntity entity);

    long checkIntervalForSpecification(String specialization,String timeInterval);

    long checkInterval(String email, String interval);


}

package com.xworkz.hospital.repository;


import com.xworkz.hospital.entity.BloodGroupEntity;

import java.util.List;

public interface PatientRepository {
    List<BloodGroupEntity> getAllBloodGroup();

    String getTimeSlot(String email);
}

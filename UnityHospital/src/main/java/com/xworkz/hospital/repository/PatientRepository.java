package com.xworkz.hospital.repository;


import com.xworkz.hospital.dto.PatientDto;
import com.xworkz.hospital.entity.BloodGroupEntity;
import com.xworkz.hospital.entity.PateintEntity;

import java.util.List;

public interface PatientRepository {
    List<BloodGroupEntity> getAllBloodGroup();

    List<String> getTimeSlot(String email);

    boolean savePatientDetails(PateintEntity entity);
}

package com.xworkz.hospital.service;

import com.xworkz.hospital.dto.BloodGroupDto;
import com.xworkz.hospital.dto.PatientDto;


import java.util.List;

public interface PatientService {
    List<BloodGroupDto> getAllBloodGroup();

    List<String> getTimeSlot(String email);

   boolean savePatientDetails(PatientDto dto);
}

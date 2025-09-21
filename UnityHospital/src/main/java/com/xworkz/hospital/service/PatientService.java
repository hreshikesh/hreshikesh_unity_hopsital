package com.xworkz.hospital.service;

import com.xworkz.hospital.dto.BloodGroupDto;


import java.util.List;

public interface PatientService {
    List<BloodGroupDto> getAllBloodGroup();

   String getTimeSlot(String email);
}

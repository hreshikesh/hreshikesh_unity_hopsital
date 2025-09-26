package com.xworkz.hospital.service;

import com.xworkz.hospital.dto.BloodGroupDto;
import com.xworkz.hospital.dto.DoctorTimeSlotDto;
import com.xworkz.hospital.dto.PatientDto;


import java.util.List;

public interface PatientService {
    List<BloodGroupDto> getAllBloodGroup();

    List<DoctorTimeSlotDto> getTimeSlot(int id);

   boolean savePatientDetails(PatientDto dto);
}

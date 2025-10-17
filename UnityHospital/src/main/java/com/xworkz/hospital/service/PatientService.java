package com.xworkz.hospital.service;

import com.xworkz.hospital.dto.BloodGroupDto;
import com.xworkz.hospital.dto.DoctorTimeSlotDto;
import com.xworkz.hospital.dto.PatientDto;


import java.io.IOException;
import java.util.List;

public interface PatientService {
    List<BloodGroupDto> getAllBloodGroup();

    List<DoctorTimeSlotDto> getTimeSlot(int id);

   boolean savePatientDetails(PatientDto dto) throws IOException;

   List<PatientDto> getPatient(int id,int slotId);

    PatientDto getPatientDetails(int id);

}

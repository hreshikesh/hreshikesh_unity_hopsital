package com.xworkz.hospital.service;

import com.xworkz.hospital.dto.DoctorDto;
import com.xworkz.hospital.dto.SpecializationDto;

import java.io.IOException;
import java.util.List;

public interface DoctorService {
    List<SpecializationDto> getAllSpecialization();

    boolean saveDoctor(DoctorDto dto) throws IOException;

    DoctorDto  searchByEmail(String email);

    boolean updateDoctor(DoctorDto dto) throws IOException;

    List<DoctorDto> getAllDoctor();

    long getEmailCount(String email);

    boolean deleteDoctor(String email);

}

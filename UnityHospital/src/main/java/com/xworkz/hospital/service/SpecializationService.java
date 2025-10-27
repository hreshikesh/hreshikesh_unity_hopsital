package com.xworkz.hospital.service;

import com.xworkz.hospital.dto.EventDto;
import com.xworkz.hospital.dto.SpecializationDto;

import java.util.List;

public interface SpecializationService {
    boolean saveSpecialization(SpecializationDto dto);

    List<SpecializationDto> getAllSpecialization();

    boolean deleteSpecialization(int id);
}

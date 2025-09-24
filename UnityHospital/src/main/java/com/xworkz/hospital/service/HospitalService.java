package com.xworkz.hospital.service;

import com.xworkz.hospital.dto.DoctorDto;
import com.xworkz.hospital.dto.DoctorTimeSlotDto;
import com.xworkz.hospital.dto.SpecializationDto;
import com.xworkz.hospital.dto.TimeSlotDto;
import com.xworkz.hospital.entity.DoctorEntity;
import com.xworkz.hospital.entity.SpecializationEntity;

import java.util.List;

public interface HospitalService {
    int findEmail(String email);

    boolean findByEmail(String email);

    String sendOtp(String email);

    String verifyOtp(String Otp,String email);

    boolean saveDoctor(DoctorDto dto);

    DoctorDto  searchByEmail(String email);

    boolean updateDoctor(DoctorDto dto);

    List<DoctorDto> getAllDoctor();

    long getEmailCount(String email);

    void resetOtp(String email);

    boolean saveTimeInterval(TimeSlotDto dto);

    List<DoctorEntity>  findDoctorBySpecialization(String specialization);

   List<TimeSlotDto> findAllIntervals(String specialization);

   boolean checkInterval(String email, String interval);


    String setTimeSlot(DoctorTimeSlotDto dto);

   int checkIntervalForSpecification(String specialization,String timeInterval);

   boolean deleteDoctor(String email);

    List<SpecializationDto> getAllSpecialization();

}

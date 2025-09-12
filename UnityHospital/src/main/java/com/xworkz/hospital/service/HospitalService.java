package com.xworkz.hospital.service;

import com.xworkz.hospital.dto.DoctorDto;

public interface HospitalService {
    int findEmail(String email);

    boolean findByEmail(String email);

    boolean sendOtp(String email);

    String verifyOtp(String Otp,String email);

    boolean saveDoctor(DoctorDto dto);

    DoctorDto searchByEmail(String email);

    boolean updateDoctor(DoctorDto dto);



}

package com.xworkz.hospital.service;

public interface HospitalService {
    int findEmail(String email);

    boolean findByEmail(String email);

    boolean sendOtp(String email);

    boolean verifyOtp(String Otp);



}

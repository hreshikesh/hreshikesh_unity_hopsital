package com.xworkz.hospital.service;


public interface HospitalService {
    int findEmail(String email);

    String sendOtp(String email);

    String verifyOtp(String Otp,String email);

    String findAdminNameByEmail(String email);


}

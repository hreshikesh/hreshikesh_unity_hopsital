package com.xworkz.hospital.service;

import com.xworkz.hospital.dto.PatientDto;
import com.xworkz.hospital.dto.UserDto;


public interface UserService {
    boolean checkEmail(String email);

    boolean saveUser(UserDto userDto);

    String verifyAndSendOtp(String email);

    UserDto findByEmail(String email);

    boolean verifyOtp(String otp,String email);

    void updateOTP(String email);

    boolean updateUserDetails(UserDto userDto);

}

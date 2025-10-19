package com.xworkz.hospital.service;

import com.xworkz.hospital.dto.UserDto;
import com.xworkz.hospital.entity.UserEntity;

public interface UserService {
    boolean checkEmail(String email);

    boolean saveUser(UserDto userDto);

    boolean checkMobileNumber(long phone);

    String verifyAndSendOtp(String email);

    UserDto findByEmail(String email);

    boolean verifyOtp(String otp,String email);

    void updateOTP(String email);
}

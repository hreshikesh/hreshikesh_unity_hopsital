package com.xworkz.hospital.service;

import com.xworkz.hospital.dto.UserDto;

public interface UserService {
    boolean checkEmail(String email);

    boolean saveUser(UserDto userDto);

    boolean checkMobileNumber(long phone);

    String verifyAndSendOtp(String email);
}

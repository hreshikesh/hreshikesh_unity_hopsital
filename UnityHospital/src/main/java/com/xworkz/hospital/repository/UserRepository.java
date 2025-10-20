package com.xworkz.hospital.repository;

import com.xworkz.hospital.dto.UserDto;
import com.xworkz.hospital.entity.HospitalEntity;
import com.xworkz.hospital.entity.UserEntity;

import java.util.List;

public interface UserRepository {

    long checkEmail(String email);

    boolean saveUser(UserEntity userEntity);


    UserEntity findByEmail(String email);

    void updateTable(UserEntity entity);

    List<UserEntity> getAllWithOtp();

    boolean updateUserDetails(UserEntity userEntity);




}

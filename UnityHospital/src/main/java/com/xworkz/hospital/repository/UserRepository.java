package com.xworkz.hospital.repository;

import com.xworkz.hospital.dto.UserDto;
import com.xworkz.hospital.entity.UserEntity;

public interface UserRepository {

    long checkEmail(String email);

    boolean saveUser(UserEntity userEntity);

}

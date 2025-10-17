package com.xworkz.hospital.service;

import com.xworkz.hospital.dto.UserDto;
import com.xworkz.hospital.entity.UserEntity;
import com.xworkz.hospital.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl  implements  UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    EmailService emailService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();


    @Override
    public boolean checkEmail(String email) {
      long count =userRepository.checkEmail(email);
      if(count==0L){
          return true;
      }
      return false;
    }

    @Override
    public boolean saveUser(UserDto userDto) {
        if(userDto!=null) {
            userDto.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
            UserEntity entity = new UserEntity();
            BeanUtils.copyProperties(userDto,entity);
            if(checkEmail(userDto.getUserEmail())) {
                if(userRepository.saveUser(entity)){
                    emailService.getEmail(
                            userDto.getUserEmail(),
                            "Successfully Registered to Unity Hospital",
                            "Dear " + userDto.getUserName() + ",\n\nYou have been successfully registered to Unity Hospital.\n\nThank you!"+
                                    "\n\n" +
                                    "Warm regards,\n\n" +
                                    "UnityHospital\n67th cross, Attiguppe, Bengaluru \nPhone: +91 98765 43210"
                    );
                    return true;
                }else{
                    return false;
                }
            }else{
                return false;
            }
        }
        return false;
    }
}

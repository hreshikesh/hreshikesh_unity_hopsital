package com.xworkz.hospital.service;

import com.xworkz.hospital.dto.UserDto;
import com.xworkz.hospital.entity.UserEntity;
import com.xworkz.hospital.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class UserServiceImpl  implements  UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    EmailService emailService;

    @Autowired
    HospitalService hospitalService;


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

    @Override
    public boolean checkMobileNumber(long phone) {
        long count =userRepository.checkMobileNumber(phone);
        if(count==0L
        ){
            return true;
        }
        return false;
    }

    @Override
    public String verifyAndSendOtp(String email) {
        UserEntity userEntity=  userRepository.findByEmail(email);
        if(userEntity==null){
            return "Email Not Found";
        }else{
            StringBuffer otp = new StringBuffer(6);
            Random random = new Random();
            for (int i = 0; i < 6; i++) {
                otp.append(random.nextInt(10));
            }

            emailService.getEmail(email, "Your One-Time Password (OTP) for  Login ", "Dear "+userEntity.getUserName()+" ," + "\n\nYour One-Time Password (OTP) is \n\n" + otp+"\n\nThis code is for your  login on Unity Hospital.\n\nThis OTP will expire in 2 minutes\n\nFor security, please do not share this OTP with anyone\n\nThank You,\n\nUnity Hospital\nAttiguppe,Bengalore\nPhone:+91 9876543210");
            LocalDateTime localDateTime=LocalDateTime.now().plusMinutes(2);
            userEntity.setLoginTime(localDateTime);
            userEntity.setOtp(otp.toString());
            userRepository.updateTable(userEntity);
            return "sentOtp";
        }
        }

    @Override
    public UserDto findByEmail(String email) {
        UserEntity userEntity=userRepository.findByEmail(email);
        if(userEntity!=null){
            UserDto dto=new UserDto();
            BeanUtils.copyProperties(userEntity,dto);
            return dto;
        }
        return null;
    }

    @Override
    public boolean verifyOtp(String otp, String email) {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity != null) {
            return userEntity.getOtp().equals(otp) && LocalDateTime.now().isBefore(userEntity.getLoginTime());
        } else if(userEntity.getOtp()==null){
            return false;
        }
        return false;
    }

    @Override
    public void updateOTP(String email) {
       UserEntity entity= userRepository.findByEmail(email);
       entity.setOtp(null);
       entity.setLoginTime(null);
       userRepository.updateTable(entity);
    }
}

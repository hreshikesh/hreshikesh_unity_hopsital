package com.xworkz.hospital.service;

import com.xworkz.hospital.entity.*;
import com.xworkz.hospital.repository.HospitalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class HospitalServiceImpl implements HospitalService {

    private static final Logger log = LoggerFactory.getLogger(HospitalServiceImpl.class);
    @Autowired
    HospitalRepository hopsitalRepository;
    @Autowired
    EmailService emailService;


    @Override
    public int findEmail(String email) {
        return hopsitalRepository.findEmail(email);
    }


    @Override

    public String sendOtp(String email) {
        log.info(email);
        HospitalEntity entity = hopsitalRepository.findByEmail(email);
        StringBuffer otp = new StringBuffer(6);
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            otp.append(random.nextInt(10));
        }
        if (entity==null) {
            return "false";
        } else {
            emailService.getEmail(email, "Your One-Time Password (OTP) for Admin Login ", "Dear Admin," + "\n\nYour One-Time Password (OTP) is \n\n" + otp+"\n\nThis code is for your admin login on Unity Hospital.\n\nThis OTP will expire in 2 minutes\n\nFor security, please do not share this OTP with anyone\n\nThank You,\n\nUnity Hospital\nAttiguppe,Bengalore\nPhone:+91 9876543210");
            LocalDateTime localDateTime=LocalDateTime.now().plusMinutes(2);
            entity.setLocalDateTime(localDateTime);
            entity.setOtp(otp.toString());
            hopsitalRepository.updateTable(entity);
            return "true";
        }
    }

    @Override
    public String verifyOtp(String otp, String email) {
        HospitalEntity entity = hopsitalRepository.findByEmail(email);
        LocalDateTime localDateTime=LocalDateTime.now();
        LocalDateTime entityDateAndTime=entity.getLocalDateTime();

        String otpStored=entity.getOtp();
        if (localDateTime.isAfter(entityDateAndTime)) {
            return "timeout";
        }
        else
        {
            if (otpStored.equals(otp)) {
                return "pass";
            }
            else {
                return "fail";
            }

        }
    }

    @Override
    public String findAdminNameByEmail(String email) {
        return hopsitalRepository.findAdminNameByEmail(email);
    }


}

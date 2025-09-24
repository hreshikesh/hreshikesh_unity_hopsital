package com.xworkz.hospital.service;

import com.xworkz.hospital.dto.DoctorDto;
import com.xworkz.hospital.dto.DoctorTimeSlotDto;
import com.xworkz.hospital.dto.SpecializationDto;
import com.xworkz.hospital.dto.TimeSlotDto;
import com.xworkz.hospital.entity.*;
import com.xworkz.hospital.repository.HospitalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.CompletableFuture;

@Service
public class HospitalServiceImpl implements HospitalService {

    private static final Logger log = LoggerFactory.getLogger(HospitalServiceImpl.class);
    @Autowired
    HospitalRepository hopsitalRepository;
    @Autowired
            EmailService emailService;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public int findEmail(String email) {
        return hopsitalRepository.findEmail(email);
    }

    @Override
    public boolean findByEmail(String email) {
        HospitalEntity entity = hopsitalRepository.findByEmail(email);
        if (entity.getEmail() == null) {
            return false;
        } else {
            sendOtp(email);
            return true;
        }
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
    public boolean saveDoctor(DoctorDto dto) {
        DoctorEntity entity=new DoctorEntity();
        BeanUtils.copyProperties(dto,entity);
       return hopsitalRepository.saveDoctor(entity);
    }

    @Override
    public DoctorDto searchByEmail(String email) {
        DoctorEntity doctorEntity=hopsitalRepository.searchByEmail(email);
        if(doctorEntity==null){
            return null;
        }else {
            DoctorDto dto = new DoctorDto();
            BeanUtils.copyProperties(doctorEntity, dto);
            return dto;
        }
    }

    @Override
    public boolean updateDoctor(DoctorDto dto) {
        if(dto!=null) {
            DoctorEntity entity = new DoctorEntity();
            BeanUtils.copyProperties(dto, entity);
            return hopsitalRepository.updateDoctor(entity);
        }
        return false;
    }

    @Override
    public List<DoctorDto> getAllDoctor() {
      List<DoctorEntity> entities=hopsitalRepository.getAllDoctor();
      List<DoctorDto> dtos=new ArrayList<>();
      for (DoctorEntity entity:entities){
          DoctorDto dto=new DoctorDto();
          BeanUtils.copyProperties(entity,dto);
          dtos.add(dto);
      }
      return dtos;
    }

    @Override
    public long getEmailCount(String email) {
        return hopsitalRepository.getEmailCount(email);
    }

    @Override
    public void resetOtp(String email) {
        HospitalEntity entity=hopsitalRepository.findByEmail(email);
        entity.setOtp("");
        hopsitalRepository.updateTable(entity);
    }

    @Override
    public boolean saveTimeInterval(TimeSlotDto dto) {
        TimeSlotEntity entity=new TimeSlotEntity();
        BeanUtils.copyProperties(dto,entity);
        return hopsitalRepository.saveTimeInterval(entity);
    }

    @Override
    public List<DoctorEntity>  findDoctorBySpecialization(String specialization) {
      return hopsitalRepository.findDoctorBySpecialization(specialization);
    }

    @Override
    public List<TimeSlotDto> findAllIntervals(String specialization) {
      List<TimeSlotEntity> entities=hopsitalRepository.findAllIntervals(specialization);
      if(entities==null){
          return  null;
      }else{
            List<TimeSlotDto> dtos=new ArrayList<>();
          for(TimeSlotEntity entity:entities){
              TimeSlotDto dto=new TimeSlotDto();
              BeanUtils.copyProperties(entity,dto);
              dtos.add(dto);
          }
          return dtos;
      }
    }

    @Override
    public boolean checkInterval(String email, String interval) {
        long count= hopsitalRepository.checkInterval(email,interval);
        if(count==0L){
            return true;
        }
        return false;
    }

    @Override
    public String setTimeSlot(DoctorTimeSlotDto dto){
        DoctorTimeSlotEntity entity=new DoctorTimeSlotEntity();
        if(dto!=null){
            BeanUtils.copyProperties(dto,entity);
            long count=hopsitalRepository.checkInterval(dto.getDoctorEmail(), dto.getInterval());
                if(count==0L){
                    boolean check= hopsitalRepository.setTimeSlot(entity);
                    if(check){
                        return "saveSuccess";
                    }else{
                        return "saveFail";
                    }
                }
            }
        return null;
    }

    @Override
    public int checkIntervalForSpecification(String specialization, String timeInterval) {
        long count=hopsitalRepository.checkIntervalForSpecification(specialization,timeInterval);
        int convertedCount=Math.toIntExact(count);
        return convertedCount;
    }

    @Override
    public boolean deleteDoctor(String email) {
       return hopsitalRepository.deleteDoctor(email);

    }

    @Override
    public List<SpecializationDto> getAllSpecialization() {
        List<SpecializationEntity> entity=hopsitalRepository.getAllSpecialization();
        List<SpecializationDto> dtos=new ArrayList<>();
        for (SpecializationEntity entity1:entity){
            SpecializationDto dto=new SpecializationDto();
            BeanUtils.copyProperties(entity1,dto);
            dtos.add(dto);
        }
        return dtos;
    }




}

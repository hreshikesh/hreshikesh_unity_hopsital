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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;

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

    private String fileUpload(String name, MultipartFile file) throws IOException {
        byte[] filePart= file.getBytes();
        Path path= Paths.get("D:\\unity_hospital\\"+name+System.currentTimeMillis()+".jpg");
        Files.write(path,filePart);
        return path.getFileName().toString();
    }

    @Override
    public boolean saveDoctor(DoctorDto dto) throws IOException {
        if (dto != null) {
            DoctorEntity entity = new DoctorEntity();

            BeanUtils.copyProperties(dto, entity);

            boolean status = false;
            ImageEntity entity1 =null;
            if(dto.getImage()!=null&&!dto.getImage().isEmpty()){

                entity1=new ImageEntity();

                String imageName = fileUpload(dto.getDoctorName(), dto.getImage());
                entity1.setImageName(imageName);
                entity1.setImageOriginalName(dto.getImage().getOriginalFilename());
                entity1.setImagePath("D:\\unity_hospital\\" + imageName);
                entity1.setSize(dto.getImage().getSize());

                entity1.setDoctor(entity);

            }

            entity.setImageEntity(entity1);

            status= hopsitalRepository.saveDoctor(entity);


            if (status) {
                emailService.getEmail(dto.getDoctorEmail(), "Unity Hospital: Welcome to Our Team", "Dear Dr." + dto.getDoctorName() +
                        "\n\nYour registration as part of our esteemed medical team has been successfully completed. We are confident that your expertise and dedication will contribute immensely to the care and well-being of our patients" +
                        "\n\nYour Registration Details:" + "\n" +
                        "Name : " + dto.getDoctorName() + "\n" +
                        "Email : " + dto.getDoctorEmail() + "\n" +
                        "Specialization : " + dto.getSpecialization() + "\n\n" +
                        "We look forward to your valuable contributions and to working together to provide the highest quality healthcare.\n" +
                        "\n" +
                        "Once again, welcome to Unity Hospital!" +
                        "\n\n" +
                        "Warm regards,\n\n" +
                        "Admin Team\nUnityHospital\n67th cross, Attiguppe, Bengaluru \nPhone: +91 98765 43210"
                );
                return true;
            }
            }
            return false;
        }


    @Override
    public DoctorDto searchByEmail(String email) {
        DoctorEntity doctorEntity=hopsitalRepository.searchByEmail(email);
        if(doctorEntity==null){
            return null;
        }else {
            DoctorDto dto = new DoctorDto();
            BeanUtils.copyProperties(doctorEntity, dto);
            if(doctorEntity.getImageEntity()!=null && doctorEntity.getImageEntity().getImageName()!=null){
                dto.setImagePath(doctorEntity.getImageEntity().getImageName());
            }
            return dto;
        }
    }

    @Override
    public boolean updateDoctor(DoctorDto dto) throws IOException {
        if(dto == null) return false;

        DoctorEntity doctorEntity = hopsitalRepository.searchByEmail(dto.getDoctorEmail());

        if(doctorEntity == null) return false;

        doctorEntity.setDoctorName(dto.getDoctorName());
        doctorEntity.setDoctorPhone(dto.getDoctorPhone());
        doctorEntity.setSpecialization(dto.getSpecialization());
        doctorEntity.setQualification(dto.getQualification());
        doctorEntity.setExperience(dto.getExperience());


        if(dto.getImage() != null && !dto.getImage().isEmpty()){
            String imageName = fileUpload(dto.getDoctorName(), dto.getImage());

            ImageEntity imageEntity = doctorEntity.getImageEntity();

            if(imageEntity == null){
                imageEntity = new ImageEntity();
            }

            imageEntity.setImageName(imageName);
            imageEntity.setImageOriginalName(dto.getImage().getOriginalFilename());
            imageEntity.setImagePath("D:\\unity_hospital\\" + imageName);
            imageEntity.setSize(dto.getImage().getSize());


            doctorEntity.setImageEntity(imageEntity);
            imageEntity.setDoctor(doctorEntity);
        }

        return hopsitalRepository.updateDoctor(doctorEntity);

    }

    @Override
    public List<DoctorDto> getAllDoctor() {
      List<DoctorEntity> entities=hopsitalRepository.getAllDoctor();
      List<DoctorDto> dtos=new ArrayList<>();
      for (DoctorEntity entity:entities){
          DoctorDto dto=new DoctorDto();

          BeanUtils.copyProperties(entity,dto);
          if (entity.getImageEntity() != null && entity.getImageEntity().getImageName() != null) {
              dto.setImagePath(entity.getImageEntity().getImageName());
              log.info(dto.getImagePath());
          }

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
        DoctorTimeSlotEntity doctorTimeSlotEntity=new DoctorTimeSlotEntity();
        if(dto!=null){
            long count=hopsitalRepository.checkInterval(dto.getDoctorEmail(), dto.getInterval());
                if(count==0L){
                    DoctorEntity doctorEntity=hopsitalRepository.searchByEmail(dto.getDoctorEmail());
                    doctorTimeSlotEntity.setInterval(dto.getInterval());
                    doctorTimeSlotEntity.setDoctorName(dto.getDoctorName());
                    doctorTimeSlotEntity.setDoctorEmail(dto.getDoctorEmail());

                    doctorTimeSlotEntity.setDoctor(doctorEntity);

                    doctorEntity.getDoctorTimeSlotEntities().add(doctorTimeSlotEntity);

                    boolean check= hopsitalRepository.setTimeSlot(doctorTimeSlotEntity);
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

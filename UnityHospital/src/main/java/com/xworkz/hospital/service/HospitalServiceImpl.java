package com.xworkz.hospital.service;

import com.xworkz.hospital.dto.DoctorDto;
import com.xworkz.hospital.entity.DoctorEntity;
import com.xworkz.hospital.entity.HospitalEntity;
import com.xworkz.hospital.repository.HospitalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

@Service
public class HospitalServiceImpl implements HospitalService {

    private static final Logger log = LoggerFactory.getLogger(HospitalServiceImpl.class);
    @Autowired
    HospitalRepository hopsitalRepository;

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

    public boolean sendOtp(String email) {
        log.info(email);
        HospitalEntity entity = hopsitalRepository.findByEmail(email);
        StringBuffer otp = new StringBuffer(6);
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            otp.append(random.nextInt(10));
        }
        if (otp.equals(" ")) {
            return false;
        } else {
            getEmail(email, "OTP for Sigin", "Dear Admin," + "\nThe OTP is " + otp);
            LocalDateTime localDateTime=LocalDateTime.now().plusSeconds(120);
            entity.setLocalDateTime(localDateTime);
            entity.setOtp(otp.toString());
            hopsitalRepository.updateTable(entity);
            return true;
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
        DoctorEntity entity=new DoctorEntity();
        BeanUtils.copyProperties(dto,entity);
        return hopsitalRepository.updateDoctor(entity);
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


    private void getEmail(String email, String subject, String body) {
        final String username = "ailhreshikesh@gmail.com";
        final String password = "mfbl tuzg xjsl zilu";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(email)
            );
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

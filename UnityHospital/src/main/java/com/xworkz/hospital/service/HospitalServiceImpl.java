package com.xworkz.hospital.service;

import com.xworkz.hospital.entity.HospitalEntity;
import com.xworkz.hospital.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Random;

@Service
public class HospitalServiceImpl implements HospitalService {

    @Autowired
    HospitalRepository hopsitalRepository;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public int findEmail(String email) {
        return hopsitalRepository.findEmail(email);
    }

    @Override
    public boolean findByEmail(String email) {
        HospitalEntity entity=hopsitalRepository.findByEmail(email);
        if (entity.getEmail()==null){
            return  false;
        }else {
            sendOtp(email);
            return true;
        }
    }
    private String otpgenerated="";
    @Override

    public boolean sendOtp(String email) {
        StringBuffer otp = new StringBuffer(6);
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            otp.append(random.nextInt(10));
        }
        otpgenerated=otp.toString();
        if (otp.equals(" ")){
            return false;
        }else {
            getEmail(email, "OTP for Sigin", "Dear Admin" + "\nThe OTP is " + otp);
            return true;
        }
    }

    @Override
    public boolean verifyOtp(String otp) {
         if(otpgenerated.equals(otp)){
             otpgenerated="";
             return true;
         }else return false;
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

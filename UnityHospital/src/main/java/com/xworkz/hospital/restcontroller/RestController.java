package com.xworkz.hospital.restcontroller;

import com.xworkz.hospital.dto.DoctorDto;
import com.xworkz.hospital.service.HospitalService;
import com.xworkz.hospital.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@org.springframework.web.bind.annotation.RestController
@RequestMapping("/")
public class RestController {
    @Autowired
    HospitalService service;
    @Autowired
    PatientService patientService;

    @GetMapping("checkEmail/{email}")
    public String verifyEmail(@PathVariable String email) {
        int count = service.findEmail(email);
        if (count == 1) {
            return " ";
        } else return "Email Not Present";
    }

    @GetMapping("sendOtp/{email}")
    public String verifyAndSendOtp(@PathVariable String email, HttpSession session) {
        String sent = service.sendOtp(email);
        session.setAttribute("adminEmail1", email);
        if (sent.equals("true")) {
            return "OTP sent successfully";
        } else {
            return "OTP not sent check your Email!";
        }

    }

    @GetMapping("verifyOtp/{otp}")
    public String verifyOtp(@PathVariable String otp, HttpSession session) {
        String check = service.verifyOtp(otp, (String) session.getAttribute("adminEmail1"));
        return check;
    }

    @GetMapping("resetTimeOtp")
    public String resendOtp(HttpSession session) {
        service.sendOtp((String) session.getAttribute("adminEmail1"));
        return "OTP resent.";
    }


    @GetMapping("checkDoctorEmail/{doctorEmail}")
    public String checkDoctorEmail(@PathVariable String doctorEmail) {
        long count = service.getEmailCount(doctorEmail);
        if (count == 0L) {
            return "";
        } else {
            return "Email already exists Present";
        }
    }

    @GetMapping("checkInterval/{specialization}/{timeInterval}")
    public String checkIntervalPresent(@PathVariable String specialization, @PathVariable String timeInterval) {
        int count = service.checkIntervalForSpecification(specialization, timeInterval);
        log.info(specialization);
        log.info(String.valueOf(count));
        if (count >= 1) {
            return "Booked";
        } else {
            return "available";
        }
    }


    @GetMapping("fetchDoctor/{specialization}")
    public String fetchDoctor(@PathVariable String specialization, Model model) {
        log.info(specialization);
        List<DoctorDto> doctor = service.getAllDoctor();
        if (doctor.isEmpty()) {
            return "No Doctors Found";
        }
        List<String> matchedDoctor = new ArrayList<>();
        for (DoctorDto dto : doctor) {
            if (specialization.equals(dto.getSpecialization())) {
                matchedDoctor.add(dto.getDoctorName()+"|"+dto.getDoctorEmail());
            }
        }
        if (matchedDoctor.isEmpty()) {
            return "No doctors";
        } else {
            return String.join(",", matchedDoctor);
        }
    }



    @GetMapping("/fetchTimeSlot")
    public ResponseEntity<String> getTimeSlot(@RequestParam String email){
               List<String> interval=patientService.getTimeSlot(email);
               if(interval==null ||interval.isEmpty()){
                   return ResponseEntity.ok("Not Assigned");
               }else{
                   return ResponseEntity.ok(String.join(",",interval));
               }
       }


       @GetMapping("checkSlot")
       public ResponseEntity<String> checkSlotAssigned(@RequestParam String email,@RequestParam String interval){
        boolean status=service.checkInterval(email,interval);
        if(status){
          return   ResponseEntity.ok("notset");
        }
        return ResponseEntity.ok("set");
       }

}

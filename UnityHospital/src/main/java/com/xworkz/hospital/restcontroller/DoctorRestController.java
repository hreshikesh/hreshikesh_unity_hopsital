package com.xworkz.hospital.restcontroller;

import com.xworkz.hospital.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DoctorRestController {
    @Autowired
    HospitalService service;

    @GetMapping("checkDoctorEmail/{doctorEmail}")
    public String checkDoctorEmail(@PathVariable String doctorEmail) {
        long count = service.getEmailCount(doctorEmail);
        if (count == 0L) {
            return "";
        } else {
            return "Email already exists Present";
        }
    }

}

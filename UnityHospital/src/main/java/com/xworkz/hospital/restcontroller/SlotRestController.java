package com.xworkz.hospital.restcontroller;

import com.xworkz.hospital.dto.DoctorDto;
import com.xworkz.hospital.dto.DoctorTimeSlotDto;
import com.xworkz.hospital.service.HospitalService;
import com.xworkz.hospital.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
@RestController
@Slf4j
public class SlotRestController {

    @Autowired
    HospitalService service;


    @Autowired
    PatientService patientService;.


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
                matchedDoctor.add(dto.getDoctorName()+"|"+dto.getId());
            }
        }
        if (matchedDoctor.isEmpty()) {
            return "No doctors";
        } else {
            return String.join(",", matchedDoctor);
        }
    }



    @GetMapping("/fetchTimeSlot")
    public ResponseEntity<String> getTimeSlot(@RequestParam int id){
        List<DoctorTimeSlotDto> interval=patientService.getTimeSlot(id);
        if(interval==null ||interval.isEmpty()){
            return ResponseEntity.ok("Not Assigned");
        }else{
            List<String> mappedValues=new ArrayList<>();
            for(DoctorTimeSlotDto dto:interval){
                log.info(dto.getInterval()+"|"+dto.getId());
                mappedValues.add(dto.getInterval()+","+dto.getId());
            }
            return ResponseEntity.ok(String.join("|",mappedValues));
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

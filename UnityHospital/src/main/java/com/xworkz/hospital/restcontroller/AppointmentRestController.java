package com.xworkz.hospital.restcontroller;

import com.xworkz.hospital.dto.DoctorTimeSlotDto;
import com.xworkz.hospital.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/")
public class AppointmentRestController {
    @Autowired
    PatientService patientService;

    @GetMapping(value = "fetchAssignedSlot",produces = "application/json")
    public ResponseEntity<List<DoctorTimeSlotDto>> getAssignedSlot(@RequestParam int id){
        log.info(String.valueOf(id));
        if (id <= 0) {
            return ResponseEntity.badRequest().build();
        }
        List<DoctorTimeSlotDto> dtos = patientService.getTimeSlot(id);
        log.info(dtos.toString());
        if (dtos == null || dtos.isEmpty()) {
            return ResponseEntity.noContent().build();//satus 204 basically data related to that id not found
        }

        return ResponseEntity.ok(dtos);

    }
}

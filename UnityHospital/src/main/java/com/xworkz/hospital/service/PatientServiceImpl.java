package com.xworkz.hospital.service;

import com.xworkz.hospital.dto.BloodGroupDto;
import com.xworkz.hospital.dto.PatientDto;
import com.xworkz.hospital.entity.BloodGroupEntity;
import com.xworkz.hospital.entity.PateintEntity;
import com.xworkz.hospital.repository.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PatientServiceImpl  implements  PatientService{
    private static final Logger log = LoggerFactory.getLogger(PatientServiceImpl.class);
    @Autowired
    PatientRepository repository;

    @Autowired
    EmailService emailService;

    @Override
    public List<BloodGroupDto> getAllBloodGroup() {
        List<BloodGroupEntity> entities=repository.getAllBloodGroup();
        log.info(entities.toString());
        List<BloodGroupDto> dtos=new ArrayList<>();
        for(BloodGroupEntity entity:entities){
            BloodGroupDto bloodGroupDto=new BloodGroupDto();
            BeanUtils.copyProperties(entity,bloodGroupDto);
            dtos.add(bloodGroupDto);
        }
        return dtos;
    }

    @Override
    public List<String> getTimeSlot(String email) {
        return repository.getTimeSlot(email);
    }

    @Override
    public boolean savePatientDetails(PatientDto dto) {
        if(dto!=null){
            PateintEntity entity=new PateintEntity();
            entity.setLocalDateTime(LocalDateTime.now());
            BeanUtils.copyProperties(dto,entity);
            if(repository.savePatientDetails(entity)){
                emailService.getEmail(dto.getEmail(),"Appointment Confirmation for "+dto.getName()+" – Unity Hospital","Dear "+dto.getName()+","+"\n\nYour Appointment has been scheduled. Please go through the details"+
                        "\n\nRegistration ID : "+dto.getRegistrationId()+"\n\nPatient Name : "+dto.getName()+"\nPatient Age : "+dto.getAge()+"\nPatient Disease/Symptoms : "+dto.getDisease()+
                        "\nDoctor Name : "+dto.getDoctorName()+"\nDoctor Speciality : "+dto.getSpecialization()+"\nDoctor Timings : "+dto.getSlot()+
                        "\n\nFor Further enquiries please visit the reception"+
                        "\n\nThanks & Regards\n\nUnity Hospital\n67th cross, Attiguppe, Bengaluru \nPhone: +91 98765 43210"
                );
                return true;
            }
        }
        return false;
    }
}

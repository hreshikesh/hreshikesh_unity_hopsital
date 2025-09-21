package com.xworkz.hospital.service;

import com.xworkz.hospital.dto.BloodGroupDto;
import com.xworkz.hospital.entity.BloodGroupEntity;
import com.xworkz.hospital.repository.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class PatientServiceImpl  implements  PatientService{
    private static final Logger log = LoggerFactory.getLogger(PatientServiceImpl.class);
    @Autowired
    PatientRepository repository;

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
    public String getTimeSlot(String email) {
        return repository.getTimeSlot(email);
    }
}

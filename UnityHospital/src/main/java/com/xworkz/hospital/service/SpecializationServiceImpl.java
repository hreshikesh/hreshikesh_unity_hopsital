package com.xworkz.hospital.service;

import com.xworkz.hospital.dto.SpecializationDto;
import com.xworkz.hospital.entity.SpecializationEntity;
import com.xworkz.hospital.repository.SpecializationRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpecializationServiceImpl implements  SpecializationService {
    @Autowired
    SpecializationRepository specializationRepository;


    @Override
    public boolean saveSpecialization(SpecializationDto dto) {
       if(dto!=null){
           SpecializationEntity entity=new SpecializationEntity();
           BeanUtils.copyProperties(dto,entity);
           return specializationRepository.saveSpecialization(entity);
       }else{
           return false;
       }
    }
}

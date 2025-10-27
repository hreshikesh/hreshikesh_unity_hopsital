package com.xworkz.hospital.service;

import com.xworkz.hospital.dto.EventDto;
import com.xworkz.hospital.dto.SpecializationDto;
import com.xworkz.hospital.entity.SpecializationEntity;
import com.xworkz.hospital.repository.SpecializationRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<SpecializationDto> getAllSpecialization() {
        List<SpecializationEntity> entities = specializationRepository.getAllSpecialization();
        List<SpecializationDto> specializationDtos = new ArrayList<>();
        if (entities != null && !entities.isEmpty()) {
            for (SpecializationEntity specializationEntity : entities) {
                if (specializationEntity != null) {
                   SpecializationDto dto=new SpecializationDto();
                   dto.setId(specializationEntity.getId());
                   dto.setSpecialization(specializationEntity.getSpecialization());

                    specializationDtos.add(dto);
                }
            }
        }
        return specializationDtos;
    }

    @Override
    public boolean deleteSpecialization(int id) {
        return specializationRepository.deleteSpecialization(id);
    }
}

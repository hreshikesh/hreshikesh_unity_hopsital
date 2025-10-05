package com.xworkz.hospital.service;

import com.xworkz.hospital.dto.DoctorTimeSlotDto;
import com.xworkz.hospital.dto.TimeSlotDto;
import com.xworkz.hospital.entity.DoctorEntity;
import com.xworkz.hospital.entity.DoctorTimeSlotEntity;
import com.xworkz.hospital.entity.TimeSlotEntity;
import com.xworkz.hospital.repository.DoctorRepositoryImpl;
import com.xworkz.hospital.repository.HospitalRepository;
import com.xworkz.hospital.repository.SlotRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class SlotServiceImpl implements  SlotService{
    @Autowired
    HospitalRepository hopsitalRepository;

    @Autowired
    DoctorRepositoryImpl doctorRepository;

    @Autowired
    SlotRepositoryImpl slotRepository;


    @Override
    public boolean saveTimeInterval(TimeSlotDto dto) {
        TimeSlotEntity entity=new TimeSlotEntity();
        BeanUtils.copyProperties(dto,entity);
        return slotRepository.saveTimeInterval(entity);
    }

    @Override
    public List<DoctorEntity> findDoctorBySpecialization(String specialization) {
        return slotRepository.findDoctorBySpecialization(specialization);
    }

    @Override
    public List<TimeSlotDto> findAllIntervals(String specialization) {
        List<TimeSlotEntity> entities=slotRepository.findAllIntervals(specialization);
        if(entities==null){
            return  null;
        }else{
            List<TimeSlotDto> dtos=new ArrayList<>();
            for(TimeSlotEntity entity:entities){
                TimeSlotDto dto=new TimeSlotDto();
                BeanUtils.copyProperties(entity,dto);
                dtos.add(dto);
            }
            return dtos;
        }
    }

    @Override
    public boolean checkInterval(String email, String interval) {
        long count= slotRepository.checkInterval(email,interval);
        if(count==0L){
            return true;
        }
        return false;
    }





    @Override
    public String setTimeSlot(DoctorTimeSlotDto dto){
        DoctorTimeSlotEntity doctorTimeSlotEntity=new DoctorTimeSlotEntity();
        if(dto!=null){
            long count=slotRepository.checkInterval(dto.getDoctorEmail(), dto.getInterval());
            if(count==0L){
                DoctorEntity doctorEntity=doctorRepository.searchByEmail(dto.getDoctorEmail());
                doctorTimeSlotEntity.setInterval(dto.getInterval());
                doctorTimeSlotEntity.setDoctorName(dto.getDoctorName());
                doctorTimeSlotEntity.setDoctorEmail(dto.getDoctorEmail());

                doctorTimeSlotEntity.setDoctor(doctorEntity);

                doctorEntity.getDoctorTimeSlotEntities().add(doctorTimeSlotEntity);

                boolean check= slotRepository.setTimeSlot(doctorTimeSlotEntity);
                if(check){
                    return "saveSuccess";
                }else{
                    return "saveFail";
                }
            }
        }
        return null;
    }

    @Override
    public int checkIntervalForSpecification(String specialization, String timeInterval) {
        long count=slotRepository.checkIntervalForSpecification(specialization,timeInterval);
        int convertedCount=Math.toIntExact(count);
        return convertedCount;
    }




}

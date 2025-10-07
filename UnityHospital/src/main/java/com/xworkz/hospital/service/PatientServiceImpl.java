package com.xworkz.hospital.service;

import com.xworkz.hospital.dto.BloodGroupDto;
import com.xworkz.hospital.dto.DoctorTimeSlotDto;
import com.xworkz.hospital.dto.PatientDto;
import com.xworkz.hospital.entity.*;
import com.xworkz.hospital.repository.DoctorRepository;
import com.xworkz.hospital.repository.HospitalRepository;
import com.xworkz.hospital.repository.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class PatientServiceImpl  implements  PatientService{
    private static final Logger log = LoggerFactory.getLogger(PatientServiceImpl.class);
    @Autowired
    PatientRepository repository;

    @Autowired
    EmailService emailService;


    @Autowired
    DoctorRepository doctorRepository;

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
    public List<DoctorTimeSlotDto> getTimeSlot(int id) {
        List<DoctorTimeSlotEntity> entities=repository.getTimeSlot(id);

        List<DoctorTimeSlotDto> dtos=new ArrayList<>();
        for(DoctorTimeSlotEntity entity:entities){
            DoctorTimeSlotDto dto=new DoctorTimeSlotDto();
            BeanUtils.copyProperties(entity,dto);
            dtos.add(dto);
        }
        return dtos;
    }


    private String patientProfileUpload(String name, MultipartFile file) throws IOException {
        byte[] filePart= file.getBytes();
        Path path= Paths.get("D:\\pateintProfile\\"+name+System.currentTimeMillis()+".jpg");
        Files.write(path,filePart);
        return path.getFileName().toString();
    }


    private String patientSymptomsUpload(String name, MultipartFile file) throws IOException {
        byte[] filePart= file.getBytes();
        Path path= Paths.get("D:\\patientSymptoms\\"+name+System.currentTimeMillis()+".jpg");
        Files.write(path,filePart);
        return path.getFileName().toString();
    }



    @Override
    public boolean savePatientDetails(PatientDto dto) throws IOException {
        if(dto!=null){
            PateintEntity pateintEntity=new PateintEntity();
            pateintEntity.setRegistrationId(dto.getRegistrationId());
            pateintEntity.setName(dto.getName());
            pateintEntity.setAge(dto.getAge());
            pateintEntity.setBloodGroup(dto.getBloodGroup());
            pateintEntity.setEmail(dto.getEmail());
            pateintEntity.setPhone(dto.getPhone());
            pateintEntity.setAddress(dto.getAddress());
            pateintEntity.setDisease(dto.getDisease());
            pateintEntity.setSpecialization(dto.getSpecialization());
            pateintEntity.setFees(dto.getFees());
            pateintEntity.setDoctorName(dto.getDoctorName());
            pateintEntity.setSlot(dto.getSlot());

           DoctorEntity entity1= doctorRepository.findById(dto.getDoctorId());
           log.info(entity1.toString());
           if(entity1!=null){
               pateintEntity.setDoctor(entity1);
           }

         DoctorTimeSlotEntity entity2=  repository.getInterval(dto.getSlotId());
           if(entity2!=null){
               pateintEntity.setSlotEntity(entity2);
           }

            PatientProfileEntity patientProfileEntity=null;
           if(dto.getProfile()!=null && !dto.getProfile().isEmpty()){

               patientProfileEntity=new PatientProfileEntity();
               String imageName=patientProfileUpload(dto.getName(), dto.getProfile());

               patientProfileEntity.setSize(dto.getProfile().getSize());
               patientProfileEntity.setImagePath("D:\\pateintProfile\\"+imageName);
               patientProfileEntity.setImageOriginalName(dto.getProfile().getOriginalFilename());
               patientProfileEntity.setImageName(imageName);

               patientProfileEntity.setPateintEntity(pateintEntity);

               pateintEntity.setPatientProfileEntity(patientProfileEntity);

           }


           if(dto.getSymptomsImage()!=null && !dto.getSymptomsImage().isEmpty()){

               for(MultipartFile file:dto.getSymptomsImage()){
                   PatientSymtomsImageEntity symtomsImageEntity=new PatientSymtomsImageEntity();
                      String imageName=  patientProfileUpload(dto.getName(),file);
                      symtomsImageEntity.setImageOriginalName(file.getOriginalFilename());
                      symtomsImageEntity.setImageName(imageName);
                      symtomsImageEntity.setImagePath("D:\\patientSymptoms\\"+imageName);
                      symtomsImageEntity.setSize(file.getSize());
                      symtomsImageEntity.setPateintEntity(pateintEntity);

                      pateintEntity.getPatientSymtomsImageEntityList().add(symtomsImageEntity);
               }


           }



            if(repository.savePatientDetails(pateintEntity)){
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

    @Override
    public List<PatientDto> getPatient(int id) {
        List<PateintEntity> pateintEntities=repository.getPatient(id);
        List<PatientDto> patientDtos=new ArrayList<>();
        if(pateintEntities!=null&&!pateintEntities.isEmpty()){
            for(PateintEntity pateintEntity:pateintEntities){
                PatientDto dto=new PatientDto();
                dto.setName(pateintEntity.getName());
                dto.setId(pateintEntity.getId());
                dto.setRegistrationId(pateintEntity.getRegistrationId());
                patientDtos.add(dto);
            }
        }
        return patientDtos;
    }
}

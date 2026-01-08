package com.xworkz.hospital.service;

import com.xworkz.hospital.dto.DoctorDto;
import com.xworkz.hospital.dto.SpecializationDto;
import com.xworkz.hospital.entity.DoctorEntity;
import com.xworkz.hospital.entity.DoctorImageEntity;
import com.xworkz.hospital.entity.SpecializationEntity;
import com.xworkz.hospital.repository.DoctorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class DoctorServiceImpl implements DoctorService {




    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    EmailService emailService;


    private String fileUpload(String name, MultipartFile file) throws IOException {
        byte[] filePart= file.getBytes();
        Path path= Paths.get("D:\\unity_hospital\\"+name+System.currentTimeMillis()+".jpg");
        Files.write(path,filePart);
        return path.getFileName().toString();
    }

    @Override
    public boolean saveDoctor(DoctorDto dto) throws IOException {
        if (dto != null) {
            DoctorEntity entity = new DoctorEntity();

            BeanUtils.copyProperties(dto, entity);

            boolean status = false;
            DoctorImageEntity entity1 =null;
            if(dto.getImage()!=null&&!dto.getImage().isEmpty()){

                entity1=new DoctorImageEntity();

                String imageName = fileUpload(dto.getDoctorName(), dto.getImage());
                entity1.setImageName(imageName);
                entity1.setImageOriginalName(dto.getImage().getOriginalFilename());
                entity1.setImagePath("D:\\unity_hospital\\" + imageName);
                entity1.setSize(dto.getImage().getSize());

                entity1.setDoctor(entity);

            }

            entity.setDoctorImageEntity(entity1);

            status= doctorRepository.saveDoctor(entity);


            if (status) {
                emailService.getEmail(dto.getDoctorEmail(), "Unity Hospital: Welcome to Our Team", "Dear Dr." + dto.getDoctorName() +
                        "\n\nYour registration as part of our esteemed medical team has been successfully completed. We are confident that your expertise and dedication will contribute immensely to the care and well-being of our patients" +
                        "\n\nYour Registration Details:" + "\n" +
                        "Name : " + dto.getDoctorName() + "\n" +
                        "Email : " + dto.getDoctorEmail() + "\n" +
                        "Specialization : " + dto.getSpecialization() + "\n\n" +
                        "We look forward to your valuable contributions and to working together to provide the highest quality healthcare.\n" +
                        "\n" +
                        "Once again, welcome to Unity Hospital!" +
                        "\n\n" +
                        "Warm regards,\n\n" +
                        "Admin Team\nUnityHospital\n67th cross, Attiguppe, Bengaluru \nPhone: +91 98765 43210"
                );
                return true;
            }
        }
        return false;
    }

    @Override
    public DoctorDto searchByEmail(String email) {
        DoctorEntity doctorEntity=doctorRepository.searchByEmail(email);
        if(doctorEntity==null){
            return null;
        }else {
            DoctorDto dto = new DoctorDto();
            BeanUtils.copyProperties(doctorEntity, dto);
            if(doctorEntity.getDoctorImageEntity()!=null && doctorEntity.getDoctorImageEntity().getImageName()!=null){
                dto.setImagePath(doctorEntity.getDoctorImageEntity().getImageName());
            }
            return dto;
        }
    }


    @Override
    public boolean updateDoctor(DoctorDto dto) throws IOException {
        if(dto == null) return false;

        DoctorEntity doctorEntity = doctorRepository.searchByEmail(dto.getDoctorEmail());

        if(doctorEntity == null) return false;

        doctorEntity.setDoctorName(dto.getDoctorName());
        doctorEntity.setDoctorPhone(dto.getDoctorPhone());
        doctorEntity.setSpecialization(dto.getSpecialization());
        doctorEntity.setQualification(dto.getQualification());
        doctorEntity.setExperience(dto.getExperience());


        if(dto.getImage() != null && !dto.getImage().isEmpty()){
            String imageName = fileUpload(dto.getDoctorName(), dto.getImage());

            DoctorImageEntity doctorImageEntity = doctorEntity.getDoctorImageEntity();

            if(doctorImageEntity == null){
                doctorImageEntity = new DoctorImageEntity();
            }

            doctorImageEntity.setImageName(imageName);
            doctorImageEntity.setImageOriginalName(dto.getImage().getOriginalFilename());
            doctorImageEntity.setImagePath("D:\\unity_hospital\\" + imageName);
            doctorImageEntity.setSize(dto.getImage().getSize());


            doctorEntity.setDoctorImageEntity(doctorImageEntity);
            doctorImageEntity.setDoctor(doctorEntity);
        }

        return doctorRepository.updateDoctor(doctorEntity);

    }


    @Override
    public List<DoctorDto> getAllDoctor(int page,int size) {
        List<DoctorEntity> entities=doctorRepository.getAllDoctor();
        int start=page*size;
        int end=Math.min(start+size,entities.size());
        if(start>end){
            return new ArrayList<>();
        }
        List<DoctorEntity> pagedEntity=entities.subList(start,end);

        List<DoctorDto> dtos=new ArrayList<>();
        for (DoctorEntity entity:pagedEntity){
            DoctorDto dto=new DoctorDto();

            BeanUtils.copyProperties(entity,dto);
            if (entity.getDoctorImageEntity() != null && entity.getDoctorImageEntity().getImageName() != null) {
                dto.setImagePath(entity.getDoctorImageEntity().getImageName());
                log.info(dto.getImagePath());
            }

            dtos.add(dto);
        }
        return dtos;
    }



    @Override
    public long getEmailCount(String email) {
        return doctorRepository.getEmailCount(email);
    }

    @Override
    public boolean deleteDoctor(String email) {
        return doctorRepository.deleteDoctor(email);

    }

    @Override
    public int getDoctorCount() {
        return doctorRepository.getAllDoctor().size();
    }




}

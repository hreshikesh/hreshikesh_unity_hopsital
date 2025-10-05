package com.xworkz.hospital.repository;

import com.xworkz.hospital.entity.DoctorEntity;
import com.xworkz.hospital.entity.SpecializationEntity;

import java.util.List;

public interface DoctorRepository {

    boolean saveDoctor(DoctorEntity entity);

    DoctorEntity searchByEmail(String email);

    boolean updateDoctor(DoctorEntity entity);

    List<DoctorEntity> getAllDoctor();

    long getEmailCount(String email);

    boolean deleteDoctor(String email);

    List<SpecializationEntity> getAllSpecialization();

    DoctorEntity findById(int id);


}

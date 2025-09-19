package com.xworkz.hospital.repository;

import com.xworkz.hospital.entity.DoctorEntity;
import com.xworkz.hospital.entity.HospitalEntity;
import com.xworkz.hospital.entity.SpecializationEntity;
import com.xworkz.hospital.entity.TimeSlotEntity;

import java.util.List;

public interface HospitalRepository {
    int findEmail(String email);

    HospitalEntity findByEmail(String email);

    void updateTable(HospitalEntity entity);

    boolean saveDoctor(DoctorEntity entity);

    DoctorEntity searchByEmail(String email);

    boolean updateDoctor(DoctorEntity entity);

    List<DoctorEntity> getAllDoctor();

    long getEmailCount(String email);

    boolean saveTimeInterval(TimeSlotEntity entity);

    List<String> findDoctorBySpecialization(String specialization);

    List<TimeSlotEntity> findAllIntervals();

    boolean setTimeSlot(String doctorName,String timeInterval);

    long checkIntervalForSpecification(String specialization,String timeInterval);

    boolean deleteDoctor(String email);

    List<SpecializationEntity> getAllSpecialization();

}

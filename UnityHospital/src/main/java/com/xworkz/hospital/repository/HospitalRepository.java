package com.xworkz.hospital.repository;

import com.xworkz.hospital.entity.*;

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

    List<DoctorEntity>  findDoctorBySpecialization(String specialization);

    List<TimeSlotEntity> findAllIntervals(String specialization);

    boolean setTimeSlot(DoctorTimeSlotEntity entity);

    long checkIntervalForSpecification(String specialization,String timeInterval);

    boolean deleteDoctor(String email);

    List<SpecializationEntity> getAllSpecialization();

    List<HospitalEntity> getAllWithOtp();


    long checkInterval(String email, String interval);


}

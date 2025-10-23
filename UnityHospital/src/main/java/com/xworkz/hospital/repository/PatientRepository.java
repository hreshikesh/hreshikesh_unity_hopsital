package com.xworkz.hospital.repository;


import com.xworkz.hospital.entity.BloodGroupEntity;
import com.xworkz.hospital.entity.DoctorTimeSlotEntity;
import com.xworkz.hospital.entity.PateintEntity;

import java.util.List;

public interface PatientRepository {
    List<BloodGroupEntity> getAllBloodGroup();

    List<DoctorTimeSlotEntity> getTimeSlot(int id);

    boolean savePatientDetails(PateintEntity entity);

    DoctorTimeSlotEntity getInterval(int id);


    List<PateintEntity> getPatient(int id, int slotId);

    PateintEntity getPatientDetails(int id);

    PateintEntity findPatientByRegistrationId(String regid);


}

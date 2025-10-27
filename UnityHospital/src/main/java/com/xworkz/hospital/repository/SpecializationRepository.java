package com.xworkz.hospital.repository;

import com.xworkz.hospital.entity.EventEntity;
import com.xworkz.hospital.entity.SpecializationEntity;

import java.util.List;

public interface SpecializationRepository {
    boolean saveSpecialization(SpecializationEntity entity);

    List<SpecializationEntity> getAllSpecialization();

    boolean deleteSpecialization(int id);
}

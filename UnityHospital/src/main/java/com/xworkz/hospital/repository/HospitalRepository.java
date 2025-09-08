package com.xworkz.hospital.repository;

import com.xworkz.hospital.entity.HospitalEntity;

public interface HospitalRepository {
    int findEmail(String email);

    HospitalEntity findByEmail(String email);

    void updateTable(HospitalEntity entity);

}

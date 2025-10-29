package com.xworkz.hospital.repository;

import com.xworkz.hospital.entity.*;

import java.util.List;

public interface HospitalRepository {
    int findEmail(String email);

    HospitalEntity findByEmail(String email);

    void updateTable(HospitalEntity entity);

    List<HospitalEntity> getAllWithOtp();

    String findAdminNameByEmail(String email);





}

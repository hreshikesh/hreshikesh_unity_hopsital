package com.xworkz.hospital.service;

import com.xworkz.hospital.entity.HospitalEntity;
import com.xworkz.hospital.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OtpSchedulerService {
    @Autowired
    private HospitalRepository hospitalRepository;

    @Scheduled(fixedRate = 5000)
    public void checkOtpExpiry() {
        List<HospitalEntity> entities = hospitalRepository.getAllWithOtp();
        LocalDateTime now = LocalDateTime.now();
        for (HospitalEntity entity : entities) {
            if (entity.getLocalDateTime() != null && now.isAfter(entity.getLocalDateTime())) {
                entity.setOtp(null);
                entity.setLocalDateTime(null);
                hospitalRepository.updateTable(entity);
            }
        }
    }

}

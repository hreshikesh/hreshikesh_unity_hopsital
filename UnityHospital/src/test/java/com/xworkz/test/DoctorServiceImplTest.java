package com.xworkz.test;


import com.xworkz.hospital.dto.DoctorDto;
import com.xworkz.hospital.service.DoctorService;
import com.xworkz.hospital.service.DoctorServiceImpl;
import org.junit.Assert;
import org.junit.Test;




public class DoctorServiceImplTest {

    DoctorService doctorService=new DoctorServiceImpl();

    @Test
    public void saveDoctor() throws Exception {
        DoctorDto doctorDto=new DoctorDto();
        doctorDto.setDoctorName("Ravi");
        doctorDto.setDoctorEmail("ravi@gmail.com");
        doctorDto.setDoctorPhone(7945620130L);
        doctorDto.setExperience(3);
        doctorDto.setQualification("MBBS");
        doctorDto.setSpecialization("Cardilogist");
        doctorDto.setImage(null);
        boolean result=doctorService.saveDoctor(doctorDto);
        Assert.assertTrue(result);

    }
}
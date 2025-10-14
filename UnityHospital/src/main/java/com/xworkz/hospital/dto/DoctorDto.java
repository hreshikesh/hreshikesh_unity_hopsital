package com.xworkz.hospital.dto;


import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDto {


    private int id;

    @NotNull(message = "Invalid data for Name")
    @Size(min = 3,max = 10,message = "Size size between 3 and 10")
    @Pattern(regexp = "^[A-Za-z]+$")
    private String doctorName;

    @NotNull(message = "Invalid data for email")
    @Pattern(regexp = "[a-z0-9]+@gmail\\.com$")
    private String doctorEmail;

    @NotNull(message = "Invalid data for phone number")
    @Min(value = 6000000000L,message = "Mobile should start from 6-9")
    @Max(value = 9999999999L,message = "mobile number should be 10 digits")
    private long doctorPhone;

    @NotNull(message = "Invalid data for specialization")
    private String specialization;

    @NotNull(message = "Invalid data for qualification")
    @Size(min = 0,max = 10,message = "qualification length should not exceed be 10")
    private String qualification;

    @NotNull(message = "Invalid data for experience")
    @Min(value = 0,message = "Experience should not be less than 0")
    @Max(value = 50,message = "Experience should not be greater than 50")
    private int experience;

    private MultipartFile image;



    private String imagePath;
}

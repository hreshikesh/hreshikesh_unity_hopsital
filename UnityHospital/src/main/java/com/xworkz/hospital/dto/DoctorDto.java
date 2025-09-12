package com.xworkz.hospital.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDto {
    @NotNull
    @Size(min = 3,max = 10,message = "Size size between 3 and 10")
    @Pattern(regexp = "^[A-Za-z]+$")
    private String doctorName;
    @javax.validation.constraints.NotNull
    @Pattern(regexp = "[A-Za-z0-9]+@gmail\\.com$")
    private String doctorEmail;
    @javax.validation.constraints.NotNull
    @Min(value = 6000000000L,message = "Mobile should start from 6-9")
    @Max(value = 9999999999L,message = "mobile number should be 10 digits")
    private long doctorPhone;
    @javax.validation.constraints.NotNull
    private String specialization;
    @javax.validation.constraints.NotNull
    private String qualification;
    @javax.validation.constraints.NotNull
    private int experience;
    private String imagePath;
}

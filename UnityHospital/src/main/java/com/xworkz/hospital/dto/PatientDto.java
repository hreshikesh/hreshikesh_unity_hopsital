package com.xworkz.hospital.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDto {

    private  String  registrationId;

    @NotNull(message = "Name should not be empty")
    @Size(min = 3,max = 15,message = "Name length should be 3-15")
    @Pattern(regexp = "^[A-Za-z]+$")
    private String name;

    @Min(value = 0,message = "Age should not be less than 0")
    @Max(value = 100,message = "Age cannot exceed 100")
    private int age;

    @NotNull(message = "Blood Group Cannot be empty")
    private  String bloodGroup;

    @NotNull(message = "Email Cannot be empty")
    @Pattern(regexp = "[a-z0-9]+@gmail\\.com$")
    private String email;

    @NotNull(message = "phone ni cannot be empty")
    @Min(value = 6000000000L,message = "Mobile should start from 6-9")
    @Max(value = 9999999999L,message = "mobile number should be 10 digits")
    private long phone;

    @NotNull(message = "Address cannot be empty")
    @Size(min = 5,max=200,message = "Address should be 5-200 words")
    private String address;

    @NotNull(message = "Disease cannot be empty")
    @Size(min = 3,max=200,message = "Disease should be 3-200 words")
    private String disease;

    @NotNull(message = "specialization cannot be empty")
    private String specialization;

    @NotNull(message = "Doctor name cannot be empty")
    private String doctorName;

    @NotNull(message = "Slot  cannot be empty")
    private String slot;

    @NotNull(message = "Disease cannot be empty")
    private String fees;


    private int slotId;
    private int doctorId;
}

package com.xworkz.hospital.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecializationDto {
    @NotNull(message = "Specialization cannot be empty")
    @Size(min = 5,max = 25,message = "Cannot exceed 25 and minimum 5 characters")
    private String specialization;
}

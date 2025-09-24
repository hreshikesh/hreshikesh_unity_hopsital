package com.xworkz.hospital.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorTimeSlotDto {

    @NotNull(message = "Name should not be empty")
    private String doctorName;
  @NotNull(message = "Email should not be empty")
    private String doctorEmail;
  @NotNull(message = "Interval should not be empty")
    private String interval;


}

package com.xworkz.hospital.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeSlotDto {
    @NotNull(message = " Specialization not chosen")
    private String specialization;
    @NotNull(message = "From Time connote be empty")
    private String fromTime;
    @NotNull(message = "To Time connote be empty")
    private String toTime;

}

package com.xworkz.hospital.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDto {

    @NotNull
    @Size(min = 5,max = 50,message = "Event details should be between 5 and 50")
    private String event;

}

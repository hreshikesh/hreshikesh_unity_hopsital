package com.xworkz.hospital.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @NotNull(message = "User Name Cannot be empty")
    @Size(min = 3,max = 25,message = "UserName should be between 3-35 characters")
    @Pattern(regexp = "^[A-Za-z]+$",message = "UserName should only have alphabets")
    private String userName;

    @NotNull(message = "User Email Cannot be empty")
    @Pattern(regexp = "[a-z0-9]+@gmail\\.com$",message = "Email should follow userEmail@gmail.com pattern")
    private String userEmail;

    @NotNull(message = "User Phone Cannot be Empty")
    @Min(value = 6000000000L, message = "Number should start from 6|7|8|9")
    @Max(value = 9999999999L, message = "Phone number must be 10 digits")
    private long phone;





}

package com.xworkz.hospital.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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


    @NotNull(message = "Password Cannot be empty")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[^A-Za-z0-9])(?=(.*\\d){3,}).{3,15}$", message = "password should have 1sp,1Caps,3 numerics")
    private String password;

}

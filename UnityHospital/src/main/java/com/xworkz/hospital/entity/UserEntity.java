package com.xworkz.hospital.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_info")
@NamedQuery(name = "UserEmailCheck",query = "select count(u.userEmail) from UserEntity u where u.userEmail=:email ")
@NamedQuery(name = "getEntityByEmail",query = "select u from UserEntity u where u.userEmail=:email ")
@NamedQuery(name = "UserPhoneCheck",query = "select count(u.phone) from UserEntity u where u.phone=:phone ")
@NamedQuery(name = "findUserAllOTP", query = "SELECT u FROM UserEntity u WHERE u.otp IS NOT NULL")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_name")
    private String userName;

  @Column(name = "user_email",unique = true)
    private String userEmail;

  @Column(name = "user_phone",unique = true)
    private long phone;


 @Column(name = "register_time")
    private LocalDateTime localDateTime=LocalDateTime.now();

 @Column(name = "user_otp")
    private String otp;

 @Column(name = "otp_valid_time")
    private LocalDateTime loginTime;

}

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
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_name")
    private String userName;

  @Column(name = "user_email",unique = true)
    private String userEmail;


 @Column(name = "user_password")
    private String password;

 @Column(name = "Saved_time")
    private LocalDateTime localDateTime=LocalDateTime.now();

}

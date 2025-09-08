package com.xworkz.hospital.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "unity_hopsital_signin")
@NamedQuery(name = "emailCount",query = "select count(e.email) from HospitalEntity e where email=:email")
@NamedQuery(name="findByEmail",query = "select e from HospitalEntity e where email=:email")
public class HospitalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "email")
    private String email;
    @Column(name="local_date_time")
    private LocalDateTime localDateTime;
}

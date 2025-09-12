package com.xworkz.hospital.entity;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name = "doctor_info")
@Data
@AllArgsConstructor
@NoArgsConstructor
@NamedQuery(name = "findByName",query = "select e from DoctorEntity e where doctorEmail=:email")
public class DoctorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "doctor_name")
    private String doctorName;
    @Column(name = "doctor_email")
    private String doctorEmail;
    @Column(name = "doctor_phone")
    private long doctorPhone;
    @Column(name = "doctor_specialization")
    private String specialization;
    @Column(name = "doctor_qualification")
    private String qualification;
    @Column(name = "doctor_experience")
    private int experience;

}

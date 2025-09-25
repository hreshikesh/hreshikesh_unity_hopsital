package com.xworkz.hospital.entity;



import lombok.*;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "doctor_info")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@NamedQuery(name = "findByName",query = "select e from DoctorEntity e where doctorEmail=:email")
@NamedQuery(name = "getAllDoctor",query = "select e from DoctorEntity e")
@NamedQuery(name = "doctorEmailCount",query = "select count(e.doctorEmail) from DoctorEntity e where doctorEmail=:email")
@NamedQuery(name = "doctorBySpecialization",query = "select e from DoctorEntity e where specialization=:specializationBy ")


public class DoctorEntity extends AuditEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "doctor_name")
    private String doctorName;

    @Column(name = "doctor_email",unique = true)
    private String doctorEmail;

    @Column(name = "doctor_phone")
    private long doctorPhone;

    @Column(name = "doctor_specialization")
    private String specialization;

    @Column(name = "doctor_qualification")
    private String qualification;

    @Column(name = "doctor_experience")
    private int experience;


    @OneToOne(mappedBy = "doctor",cascade = CascadeType.ALL,orphanRemoval = true)
    private ImageEntity imageEntity;



}

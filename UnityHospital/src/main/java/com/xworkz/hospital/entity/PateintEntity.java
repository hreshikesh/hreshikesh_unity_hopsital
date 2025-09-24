package com.xworkz.hospital.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "patient_info")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PateintEntity extends AuditEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "patient_reg_id")
    private String registrationId;


    @Column(name = "patient_name")
    private String name;

    @Column(name = "patient_age")
    private int age;

    @Column(name = "blood_group")
    private  String bloodGroup;

    @Column(name = "patient_email")
    private String email;

@Column(name = "patient_phone")
    private long phone;

    @Column(name = "patient_address")
    private String address;

    @Column(name = "patient_disease")
    private String disease;

    @Column(name = "patient_specialization")
    private String specialization;


    @Column(name = "doctor_name")
    private String doctorName;

    @Column(name = "alloted_time_slot")
    private String slot;

    @Column(name = "doctor_fees")
    private String fees;


    @Column(name = "saved_time")
    private LocalDateTime localDateTime;
}

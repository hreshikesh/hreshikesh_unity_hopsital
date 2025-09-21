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
@NamedQuery(name = "getAllDoctor",query = "select e from DoctorEntity e")
@NamedQuery(name = "doctorEmailCount",query = "select count(e.doctorEmail) from DoctorEntity e where doctorEmail=:email")
@NamedQuery(name = "doctorBySpecialization",query = "select e from DoctorEntity e where specialization=:specializationBy and e.timeSlot is null")
@NamedQuery(name = "setTimeInterval",query = "update DoctorEntity e set e.timeSlot=:timeSlot where e.doctorEmail=:email")
@NamedQuery(name = "checkInterval",query = "select count(e) from DoctorEntity e where specialization=:specializationBy and e.timeSlot=timeSlot")
@NamedQuery(name = "getInterval",query = "select e.timeSlot from  DoctorEntity e where e.doctorEmail=:email")

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
    @Column(name = "image_path")
    private String imagePath;
    @Column(name="time_slot")
    private String timeSlot;
}

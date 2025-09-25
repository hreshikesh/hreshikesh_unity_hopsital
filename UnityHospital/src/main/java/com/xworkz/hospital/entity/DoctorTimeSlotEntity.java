package com.xworkz.hospital.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "doctor_time_slot_update")
@Data
@AllArgsConstructor
@NoArgsConstructor
@NamedQuery(name = "getInterval",query = "select e.interval from DoctorTimeSlotEntity e where e.doctorEmail=:email")
@NamedQuery(name = "checkSlot",query = "select count(e) from DoctorTimeSlotEntity e where e.doctorEmail=:email and e.interval=:interval ")
@NamedQuery(name = "updatedoctorName",query = "update DoctorTimeSlotEntity e set e.doctorName=:name where e.doctorEmail=:email ")
public class DoctorTimeSlotEntity extends AuditEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "doctor_name")
    private String doctorName;
    @Column(name = "doctor_email")
    private String doctorEmail;
    @Column(name = "doctor_time_interval")
    private String interval;

@ManyToOne
    @JoinColumn(name = "doctor_id",referencedColumnName = "id",nullable = false)
    private DoctorEntity doctor;

}

package com.xworkz.hospital.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "doctor_time_slot_update")
@Data
@AllArgsConstructor
@NoArgsConstructor
@NamedQuery(name = "getInterval",query = "select e from DoctorTimeSlotEntity e where e.doctor.id=:id")
@NamedQuery(name = "checkSlot",query = "select count(e) from DoctorTimeSlotEntity e where e.doctorEmail=:email and e.interval=:interval ")
@NamedQuery(name = "updatedoctorName",query = "update DoctorTimeSlotEntity e set e.doctorName=:name where e.doctorEmail=:email ")
@NamedQuery(name = "getIntervalById",query = "select e from DoctorTimeSlotEntity e where e.id=:id")
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

    @OneToMany(mappedBy = "slotEntity",cascade = CascadeType.ALL,orphanRemoval = true)
    private  List<PateintEntity> pateintEntities=new ArrayList<>();


}

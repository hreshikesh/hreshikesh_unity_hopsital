package com.xworkz.hospital.entity;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "blood_group ")
@Data
@AllArgsConstructor
@NoArgsConstructor
@NamedQuery(name = "getBloodGroupList",query = "select e from BloodGroupEntity e")
public class BloodGroupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "patient_blood_group")
    private String bloodGroup;
}

package com.xworkz.hospital.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



import javax.persistence.*;

@Entity
@Table(name = "doctor_specialization")
@Data
@AllArgsConstructor
@NoArgsConstructor
@NamedQuery(name = "getAllSpecilaization",query = "select e from SpecializationEntity e ")
public class SpecializationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    @Column(name = "doctor_specialization")
    private String specialization;
}

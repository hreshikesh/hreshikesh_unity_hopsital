package com.xworkz.hospital.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "patient_profile_image")
public class PatientProfileEntity extends AuditEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="patient_id",referencedColumnName = "id")
    private PateintEntity pateintEntity;

    @Column(name = "image_name")
    private String imageName;

    @Column(name = "image_original_Name")
    private String  imageOriginalName;

    @Column(name = "image_path")
    private String imagePath;

    @Column(name = "image_size")
    private long size;



}

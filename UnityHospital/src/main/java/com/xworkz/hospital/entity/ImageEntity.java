package com.xworkz.hospital.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "doctor_image_entity")
public class ImageEntity extends AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="doctor_id",referencedColumnName = "id")
    private DoctorEntity doctor;

    @Column(name = "image_name")
    private String imageName;

    @Column(name = "image_original_Name")
    private String  imageOriginalName;

    @Column(name = "image_path")
    private String imagePath;

    @Column(name = "image_size")
    private long size;

}

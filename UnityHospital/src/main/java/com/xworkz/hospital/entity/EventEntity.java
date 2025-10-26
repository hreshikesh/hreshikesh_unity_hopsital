package com.xworkz.hospital.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "hospital_event")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventEntity extends  AuditEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    @Column(name = "hospital_event")
    private String event;
    @Column(name = "save_date")
    private LocalDate date;
}

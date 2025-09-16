package com.xworkz.hospital.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="doctor_time_slot")
@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name = "findAllTImeSlots",query = "select e from TimeSlotEntity e")
public class TimeSlotEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="from_time")
    private String fromTime;
    @Column(name="to_time")
    private String toTime;
}

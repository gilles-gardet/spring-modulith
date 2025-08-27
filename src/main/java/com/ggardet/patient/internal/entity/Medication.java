package com.ggardet.patient.internal.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@Table(name = "medication", schema = "patients")
public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "patient_id")
    private UUID patientId;
    @Column(nullable = false)
    private String name;
    @Column
    private String dosage;
    @Column
    private String frequency;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;
    @Column(name = "prescribing_doctor_id")
    private UUID prescribingDoctorId;
    @Column
    private String instructions;
}
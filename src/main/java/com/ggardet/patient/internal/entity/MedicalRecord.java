package com.ggardet.patient.internal.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "medical_record", schema = "patients")
public class MedicalRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "patient_id")
    private UUID patientId;
    @Column(name = "doctor_id")
    private UUID doctorId;
    @Column(name = "record_date")
    private LocalDateTime recordDate;
    @Column
    private String diagnosis;
    @Column
    private String treatment;
    @Column
    private String notes;
    @Column(name = "follow_up_required")
    private Boolean followUpRequired;
}
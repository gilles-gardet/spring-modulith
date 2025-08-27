package com.ggardet.doctor.internal.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@Table(name = "qualification", schema = "doctors")
public class Qualification {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column
    private String institution;

    @Column(name = "graduation_date")
    private LocalDate graduationDate;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
}

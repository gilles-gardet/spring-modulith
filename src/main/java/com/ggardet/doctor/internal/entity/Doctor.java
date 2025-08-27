package com.ggardet.doctor.internal.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "doctor", schema = "doctors")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;

    @Column(nullable = false)
    private String specialty;

    @Column(name = "license_number")
    private String licenseNumber;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column
    private String email;

    @Column(name = "hospital_id")
    private UUID hospitalId;

    @Column(name = "years_experience")
    private Integer yearsExperience;
}

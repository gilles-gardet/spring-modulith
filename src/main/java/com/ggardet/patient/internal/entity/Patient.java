package com.ggardet.patient.internal.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@Table(name = "patient", schema = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @Column(nullable = false)
    private String firstname;
    
    @Column(nullable = false)
    private String lastname;
    
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;
    
    @Column(name = "social_security_number")
    private String socialSecurityNumber;
    
    @Column(name = "phone_number")
    private String phoneNumber;
    
    @Column
    private String email;
    
    @Column(name = "hospital_id")
    private UUID hospitalId;
    
    @Column(name = "service_id") 
    private UUID serviceId;
}
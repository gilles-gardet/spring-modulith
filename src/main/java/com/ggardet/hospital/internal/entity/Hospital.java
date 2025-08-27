package com.ggardet.hospital.internal.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "hospital", schema = "hospitals")
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String address;
    
    @Column(name = "nb_max_services")
    private int nbMaxServices;
    
    @Column
    private String phoneNumber;
    
    @Column
    private String director;
}
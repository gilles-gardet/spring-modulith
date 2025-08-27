package com.ggardet.doctor.internal.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "specialty", schema = "doctors")
public class Specialty {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column
    private String description;

    @Column(name = "required_years_training")
    private Integer requiredYearsTraining;
}

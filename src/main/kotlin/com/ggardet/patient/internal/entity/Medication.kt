package com.ggardet.patient.internal.entity

import jakarta.persistence.*
import java.time.LocalDate
import java.util.*

@Entity
@Table(name = "medication", schema = "patients")
data class Medication(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID? = null,
    
    @Column(name = "patient_id")
    var patientId: UUID? = null,
    
    @Column(nullable = false)
    var name: String,
    
    @Column
    var dosage: String? = null,
    
    @Column
    var frequency: String? = null,
    
    @Column(name = "start_date")
    var startDate: LocalDate? = null,
    
    @Column(name = "end_date")
    var endDate: LocalDate? = null,
    
    @Column(name = "prescribing_doctor_id")
    var prescribingDoctorId: UUID? = null,
    
    @Column
    var instructions: String? = null
) {
    // JPA requires a no-arg constructor
    constructor() : this(name = "")
}
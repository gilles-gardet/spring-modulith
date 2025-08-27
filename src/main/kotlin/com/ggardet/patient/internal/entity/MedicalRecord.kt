package com.ggardet.patient.internal.entity

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "medical_record", schema = "patients")
data class MedicalRecord(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID? = null,
    
    @Column(name = "patient_id")
    var patientId: UUID? = null,
    
    @Column(name = "doctor_id")
    var doctorId: UUID? = null,
    
    @Column(name = "record_date")
    var recordDate: LocalDateTime? = null,
    
    @Column
    var diagnosis: String? = null,
    
    @Column
    var treatment: String? = null,
    
    @Column
    var notes: String? = null,
    
    @Column(name = "follow_up_required")
    var followUpRequired: Boolean? = null
) {
    // JPA requires a no-arg constructor
    constructor() : this(id = null)
}
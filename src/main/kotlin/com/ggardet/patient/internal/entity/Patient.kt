package com.ggardet.patient.internal.entity

import jakarta.persistence.*
import java.time.LocalDate
import java.util.*

@Entity
@Table(name = "patient", schema = "patients")
data class Patient(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID? = null,
    
    @Column(nullable = false)
    var firstname: String,
    
    @Column(nullable = false)
    var lastname: String,
    
    @Column(name = "birth_date", nullable = false)
    var birthDate: LocalDate,
    
    @Column(name = "social_security_number")
    var socialSecurityNumber: String? = null,
    
    @Column(name = "phone_number")
    var phoneNumber: String? = null,
    
    @Column
    var email: String? = null,
    
    @Column(name = "hospital_id")
    var hospitalId: UUID? = null,
    
    @Column(name = "service_id") 
    var serviceId: UUID? = null
) {
    // JPA requires a no-arg constructor
    constructor() : this(
        firstname = "",
        lastname = "",
        birthDate = LocalDate.now()
    )
}
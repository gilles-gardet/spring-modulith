package com.ggardet.doctor.spi.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive
import java.util.*

data class DoctorDTO(
    val id: UUID?,
    
    @field:NotBlank
    val firstname: String,
    
    @field:NotBlank
    val lastname: String,
    
    @field:NotBlank
    val specialty: String,
    
    val licenseNumber: String?,
    
    val phoneNumber: String?,
    
    val email: String?,
    
    val hospitalId: UUID?,
    
    @field:Positive
    val yearsExperience: Int?
)
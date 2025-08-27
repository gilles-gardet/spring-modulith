package com.ggardet.patient.spi.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.time.LocalDate
import java.util.*

data class PatientDTO(
    val id: UUID? = null,
    @field:NotBlank val firstname: String,
    @field:NotBlank val lastname: String,
    @field:NotNull val birthDate: LocalDate,
    val socialSecurityNumber: String? = null,
    val phoneNumber: String? = null,
    @field:Email val email: String? = null,
    val hospitalId: UUID? = null,
    val serviceId: UUID? = null
)
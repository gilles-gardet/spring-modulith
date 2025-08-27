package com.ggardet.patient.spi.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.time.LocalDate
import java.util.*

data class MedicationDTO(
    val id: UUID? = null,
    @field:NotNull val patientId: UUID,
    @field:NotBlank val name: String,
    val dosage: String? = null,
    val frequency: String? = null,
    val startDate: LocalDate? = null,
    val endDate: LocalDate? = null,
    val prescribingDoctorId: UUID? = null,
    val instructions: String? = null
)
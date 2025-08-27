package com.ggardet.patient.spi.dto

import jakarta.validation.constraints.NotNull
import java.time.LocalDateTime
import java.util.*

data class MedicalRecordDTO(
    val id: UUID? = null,
    @field:NotNull val patientId: UUID,
    @field:NotNull val doctorId: UUID,
    @field:NotNull val recordDate: LocalDateTime,
    val diagnosis: String? = null,
    val treatment: String? = null,
    val notes: String? = null,
    val followUpRequired: Boolean? = null
)
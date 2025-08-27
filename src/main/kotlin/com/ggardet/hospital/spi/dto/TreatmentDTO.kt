package com.ggardet.hospital.spi.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive
import java.util.*

data class TreatmentDTO(
    val id: UUID? = null,

    @field:NotBlank 
    val name: String,

    val description: String? = null,

    @field:Positive 
    val durationMinutes: Int? = null,

    val protocol: String? = null
)
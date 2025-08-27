package com.ggardet.doctor.spi.dto

import jakarta.validation.constraints.NotBlank
import java.time.LocalDate
import java.util.*

data class QualificationDTO(
    val id: UUID?,
    
    @field:NotBlank
    val title: String,
    
    val institution: String?,
    
    val graduationDate: LocalDate?
)
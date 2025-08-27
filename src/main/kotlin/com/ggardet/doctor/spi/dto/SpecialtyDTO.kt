package com.ggardet.doctor.spi.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive
import java.util.*

data class SpecialtyDTO(
    val id: UUID?,
    
    @field:NotBlank
    val name: String,
    
    val description: String?,
    
    @field:Positive
    val requiredYearsTraining: Int?
)
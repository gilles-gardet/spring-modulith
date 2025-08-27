package com.ggardet.hospital.spi.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import java.util.*

data class HospitalDTO(
    val id: UUID? = null,

    @field:NotBlank 
    val name: String,

    @field:NotBlank 
    val address: String,

    @field:NotNull 
    @field:Positive 
    val nbMaxServices: Int,

    val phoneNumber: String? = null,

    val director: String? = null
)
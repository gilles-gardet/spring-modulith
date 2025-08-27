package com.ggardet.doctor.spi.mapper

import com.ggardet.doctor.internal.entity.Specialty
import com.ggardet.doctor.spi.dto.SpecialtyDTO
import org.springframework.stereotype.Component

@Component
class SpecialtyMapper {
    
    fun toDto(specialty: Specialty): SpecialtyDTO {
        return SpecialtyDTO(
            id = specialty.id,
            name = specialty.name,
            description = specialty.description,
            requiredYearsTraining = specialty.requiredYearsTraining
        )
    }

    fun toEntity(specialtyDTO: SpecialtyDTO): Specialty {
        val specialty = Specialty()
        specialty.id = specialtyDTO.id
        specialty.name = specialtyDTO.name
        specialty.description = specialtyDTO.description
        specialty.requiredYearsTraining = specialtyDTO.requiredYearsTraining
        
        return specialty
    }

    fun toDtos(specialties: List<Specialty>): List<SpecialtyDTO> {
        return specialties.map(::toDto)
    }
}
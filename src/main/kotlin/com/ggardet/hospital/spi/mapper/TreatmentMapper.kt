package com.ggardet.hospital.spi.mapper

import com.ggardet.hospital.internal.entity.Treatment
import com.ggardet.hospital.spi.dto.TreatmentDTO
import org.springframework.stereotype.Component

@Component
class TreatmentMapper {
    
    fun toDto(treatment: Treatment): TreatmentDTO {
        return TreatmentDTO(
            id = treatment.id,
            name = treatment.name,
            description = treatment.description,
            durationMinutes = treatment.durationMinutes,
            protocol = treatment.protocol
        )
    }

    fun toEntity(treatmentDTO: TreatmentDTO): Treatment {
        return Treatment(
            id = treatmentDTO.id,
            name = treatmentDTO.name,
            description = treatmentDTO.description,
            durationMinutes = treatmentDTO.durationMinutes ?: 0,
            protocol = treatmentDTO.protocol
        )
    }

    fun toDtos(treatments: List<Treatment>): List<TreatmentDTO> {
        return treatments.map(::toDto)
    }
}
package com.ggardet.patient.spi.mapper

import com.ggardet.patient.internal.entity.Medication
import com.ggardet.patient.spi.dto.MedicationDTO
import org.springframework.stereotype.Component

@Component
class MedicationMapper {
    
    fun toDto(medication: Medication): MedicationDTO {
        return MedicationDTO(
            id = medication.id,
            patientId = medication.patientId!!,
            name = medication.name,
            dosage = medication.dosage,
            frequency = medication.frequency,
            startDate = medication.startDate,
            endDate = medication.endDate,
            prescribingDoctorId = medication.prescribingDoctorId,
            instructions = medication.instructions
        )
    }

    fun toEntity(medicationDTO: MedicationDTO): Medication {
        return Medication(
            id = medicationDTO.id,
            patientId = medicationDTO.patientId,
            name = medicationDTO.name,
            dosage = medicationDTO.dosage,
            frequency = medicationDTO.frequency,
            startDate = medicationDTO.startDate,
            endDate = medicationDTO.endDate,
            prescribingDoctorId = medicationDTO.prescribingDoctorId,
            instructions = medicationDTO.instructions
        )
    }

    fun toDtos(medications: List<Medication>): List<MedicationDTO> {
        return medications.map(::toDto)
    }
}
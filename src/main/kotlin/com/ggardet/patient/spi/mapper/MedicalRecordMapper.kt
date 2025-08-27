package com.ggardet.patient.spi.mapper

import com.ggardet.patient.internal.entity.MedicalRecord
import com.ggardet.patient.spi.dto.MedicalRecordDTO
import org.springframework.stereotype.Component

@Component
class MedicalRecordMapper {
    
    fun toDto(medicalRecord: MedicalRecord): MedicalRecordDTO {
        return MedicalRecordDTO(
            id = medicalRecord.id,
            patientId = medicalRecord.patientId!!,
            doctorId = medicalRecord.doctorId!!,
            recordDate = medicalRecord.recordDate!!,
            diagnosis = medicalRecord.diagnosis,
            treatment = medicalRecord.treatment,
            notes = medicalRecord.notes,
            followUpRequired = medicalRecord.followUpRequired
        )
    }

    fun toEntity(medicalRecordDTO: MedicalRecordDTO): MedicalRecord {
        return MedicalRecord(
            id = medicalRecordDTO.id,
            patientId = medicalRecordDTO.patientId,
            doctorId = medicalRecordDTO.doctorId,
            recordDate = medicalRecordDTO.recordDate,
            diagnosis = medicalRecordDTO.diagnosis,
            treatment = medicalRecordDTO.treatment,
            notes = medicalRecordDTO.notes,
            followUpRequired = medicalRecordDTO.followUpRequired
        )
    }

    fun toDtos(medicalRecords: List<MedicalRecord>): List<MedicalRecordDTO> {
        return medicalRecords.map(::toDto)
    }
}
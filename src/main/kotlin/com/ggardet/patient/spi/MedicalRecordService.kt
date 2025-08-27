package com.ggardet.patient.spi

import com.ggardet.patient.spi.dto.MedicalRecordDTO
import java.time.LocalDateTime
import java.util.*

interface MedicalRecordService {
    fun create(medicalRecordDTO: MedicalRecordDTO): MedicalRecordDTO
    fun findById(id: UUID): MedicalRecordDTO
    fun findAll(): List<MedicalRecordDTO>
    fun findByPatientId(patientId: UUID): List<MedicalRecordDTO>
    fun findByDoctorId(doctorId: UUID): List<MedicalRecordDTO>
    fun findByDateRange(start: LocalDateTime, end: LocalDateTime): List<MedicalRecordDTO>
    fun findByFollowUpRequired(followUpRequired: Boolean): List<MedicalRecordDTO>
    fun update(id: UUID, medicalRecordDTO: MedicalRecordDTO): MedicalRecordDTO
    fun deleteById(id: UUID)
}
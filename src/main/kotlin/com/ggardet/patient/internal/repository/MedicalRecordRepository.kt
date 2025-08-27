package com.ggardet.patient.internal.repository

import com.ggardet.patient.internal.entity.MedicalRecord
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime
import java.util.*

interface MedicalRecordRepository : JpaRepository<MedicalRecord, UUID> {
    fun findByPatientId(patientId: UUID): List<MedicalRecord>
    fun findByDoctorId(doctorId: UUID): List<MedicalRecord>
    fun findByRecordDateBetween(start: LocalDateTime, end: LocalDateTime): List<MedicalRecord>
    fun findByFollowUpRequired(followUpRequired: Boolean): List<MedicalRecord>
}
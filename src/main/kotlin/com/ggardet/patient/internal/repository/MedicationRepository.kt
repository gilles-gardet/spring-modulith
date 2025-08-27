package com.ggardet.patient.internal.repository

import com.ggardet.patient.internal.entity.Medication
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDate
import java.util.*

interface MedicationRepository : JpaRepository<Medication, UUID> {
    fun findByPatientId(patientId: UUID): List<Medication>
    fun findByPrescribingDoctorId(doctorId: UUID): List<Medication>
    fun findByEndDateIsNull(): List<Medication>
    fun findByEndDateAfter(date: LocalDate): List<Medication>
}
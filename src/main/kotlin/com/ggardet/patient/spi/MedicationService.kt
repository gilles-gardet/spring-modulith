package com.ggardet.patient.spi

import com.ggardet.patient.spi.dto.MedicationDTO
import java.time.LocalDate
import java.util.*

interface MedicationService {
    fun create(medicationDTO: MedicationDTO): MedicationDTO
    fun findById(id: UUID): MedicationDTO
    fun findAll(): List<MedicationDTO>
    fun findByPatientId(patientId: UUID): List<MedicationDTO>
    fun findByPrescribingDoctorId(doctorId: UUID): List<MedicationDTO>
    fun findActiveMedications(): List<MedicationDTO>
    fun findActiveMedicationsAfterDate(date: LocalDate): List<MedicationDTO>
    fun update(id: UUID, medicationDTO: MedicationDTO): MedicationDTO
    fun deleteById(id: UUID)
}
package com.ggardet.patient

import com.ggardet.patient.spi.dto.MedicalRecordDTO
import com.ggardet.patient.spi.dto.MedicationDTO
import com.ggardet.patient.spi.dto.PatientDTO
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

interface PatientServiceInterface {
    fun createPatient(patientDTO: PatientDTO): PatientDTO
    fun findPatientById(id: UUID): PatientDTO
    fun findAllPatients(): List<PatientDTO>
    fun findPatientsByHospitalId(hospitalId: UUID): List<PatientDTO>
    fun findPatientBySocialSecurityNumber(socialSecurityNumber: String): PatientDTO
    
    fun createMedicalRecord(medicalRecordDTO: MedicalRecordDTO): MedicalRecordDTO
    fun findMedicalRecordsByPatientId(patientId: UUID): List<MedicalRecordDTO>
    fun findMedicalRecordsByDateRange(start: LocalDateTime, end: LocalDateTime): List<MedicalRecordDTO>
    
    fun createMedication(medicationDTO: MedicationDTO): MedicationDTO
    fun findMedicationsByPatientId(patientId: UUID): List<MedicationDTO>
    fun findActiveMedications(): List<MedicationDTO>
    fun findActiveMedicationsAfterDate(date: LocalDate): List<MedicationDTO>
}
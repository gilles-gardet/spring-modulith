package com.ggardet.patient.internal.domain

import com.ggardet.patient.PatientServiceInterface
import com.ggardet.patient.spi.MedicalRecordService
import com.ggardet.patient.spi.MedicationService
import com.ggardet.patient.spi.PatientService
import com.ggardet.patient.spi.dto.MedicalRecordDTO
import com.ggardet.patient.spi.dto.MedicationDTO
import com.ggardet.patient.spi.dto.PatientDTO
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

@Service
internal class PatientServiceInterfaceImpl(
    private val patientService: PatientService,
    private val medicalRecordService: MedicalRecordService,
    private val medicationService: MedicationService
) : PatientServiceInterface {

    override fun createPatient(patientDTO: PatientDTO): PatientDTO {
        return patientService.create(patientDTO)
    }

    override fun findPatientById(id: UUID): PatientDTO {
        return patientService.findById(id)
    }

    override fun findAllPatients(): List<PatientDTO> {
        return patientService.findAll()
    }

    override fun findPatientsByHospitalId(hospitalId: UUID): List<PatientDTO> {
        return patientService.findByHospitalId(hospitalId)
    }

    override fun findPatientBySocialSecurityNumber(socialSecurityNumber: String): PatientDTO {
        return patientService.findBySocialSecurityNumber(socialSecurityNumber)
    }

    override fun createMedicalRecord(medicalRecordDTO: MedicalRecordDTO): MedicalRecordDTO {
        return medicalRecordService.create(medicalRecordDTO)
    }

    override fun findMedicalRecordsByPatientId(patientId: UUID): List<MedicalRecordDTO> {
        return medicalRecordService.findByPatientId(patientId)
    }

    override fun findMedicalRecordsByDateRange(start: LocalDateTime, end: LocalDateTime): List<MedicalRecordDTO> {
        return medicalRecordService.findByDateRange(start, end)
    }

    override fun createMedication(medicationDTO: MedicationDTO): MedicationDTO {
        return medicationService.create(medicationDTO)
    }

    override fun findMedicationsByPatientId(patientId: UUID): List<MedicationDTO> {
        return medicationService.findByPatientId(patientId)
    }

    override fun findActiveMedications(): List<MedicationDTO> {
        return medicationService.findActiveMedications()
    }

    override fun findActiveMedicationsAfterDate(date: LocalDate): List<MedicationDTO> {
        return medicationService.findActiveMedicationsAfterDate(date)
    }
}
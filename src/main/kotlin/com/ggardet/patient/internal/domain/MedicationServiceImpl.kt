package com.ggardet.patient.internal.domain

import com.ggardet.patient.internal.entity.Medication
import com.ggardet.patient.internal.repository.MedicationRepository
import com.ggardet.patient.spi.MedicationService
import com.ggardet.patient.spi.dto.MedicationDTO
import com.ggardet.patient.spi.exception.MedicationNotFoundException
import com.ggardet.patient.spi.mapper.MedicationMapper
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.util.*

@Service
@Transactional
class MedicationServiceImpl(
    private val medicationRepository: MedicationRepository,
    private val medicationMapper: MedicationMapper
) : MedicationService {

    override fun create(medicationDTO: MedicationDTO): MedicationDTO {
        val medication = medicationMapper.toEntity(medicationDTO)
        val savedMedication = medicationRepository.save(medication)
        return medicationMapper.toDto(savedMedication)
    }

    @Transactional(readOnly = true)
    override fun findById(id: UUID): MedicationDTO {
        val medication = medicationRepository.findById(id)
            .orElseThrow { MedicationNotFoundException(id) }
        return medicationMapper.toDto(medication)
    }

    @Transactional(readOnly = true)
    override fun findAll(): List<MedicationDTO> {
        val medications = medicationRepository.findAll()
        return medicationMapper.toDtos(medications)
    }

    @Transactional(readOnly = true)
    override fun findByPatientId(patientId: UUID): List<MedicationDTO> {
        val medications = medicationRepository.findByPatientId(patientId)
        return medicationMapper.toDtos(medications)
    }

    @Transactional(readOnly = true)
    override fun findByPrescribingDoctorId(doctorId: UUID): List<MedicationDTO> {
        val medications = medicationRepository.findByPrescribingDoctorId(doctorId)
        return medicationMapper.toDtos(medications)
    }

    @Transactional(readOnly = true)
    override fun findActiveMedications(): List<MedicationDTO> {
        val medications = medicationRepository.findByEndDateIsNull()
        return medicationMapper.toDtos(medications)
    }

    @Transactional(readOnly = true)
    override fun findActiveMedicationsAfterDate(date: LocalDate): List<MedicationDTO> {
        val medications = medicationRepository.findByEndDateAfter(date)
        return medicationMapper.toDtos(medications)
    }

    override fun update(id: UUID, medicationDTO: MedicationDTO): MedicationDTO {
        val existingMedication = medicationRepository.findById(id)
            .orElseThrow { MedicationNotFoundException(id) }
        updateMedicationFields(existingMedication, medicationDTO)
        val updatedMedication = medicationRepository.save(existingMedication)
        return medicationMapper.toDto(updatedMedication)
    }

    override fun deleteById(id: UUID) {
        if (!medicationRepository.existsById(id)) {
            throw MedicationNotFoundException(id)
        }
        medicationRepository.deleteById(id)
    }

    private fun updateMedicationFields(existingMedication: Medication, dto: MedicationDTO) {
        existingMedication.patientId = dto.patientId
        existingMedication.name = dto.name
        dto.dosage?.let { existingMedication.dosage = it }
        dto.frequency?.let { existingMedication.frequency = it }
        dto.startDate?.let { existingMedication.startDate = it }
        dto.endDate?.let { existingMedication.endDate = it }
        dto.prescribingDoctorId?.let { existingMedication.prescribingDoctorId = it }
        dto.instructions?.let { existingMedication.instructions = it }
    }
}
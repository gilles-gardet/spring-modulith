package com.ggardet.patient.internal.domain

import com.ggardet.patient.internal.entity.MedicalRecord
import com.ggardet.patient.internal.repository.MedicalRecordRepository
import com.ggardet.patient.spi.MedicalRecordService
import com.ggardet.patient.spi.dto.MedicalRecordDTO
import com.ggardet.patient.spi.exception.MedicalRecordNotFoundException
import com.ggardet.patient.spi.mapper.MedicalRecordMapper
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.util.*

@Service
@Transactional
class MedicalRecordServiceImpl(
    private val medicalRecordRepository: MedicalRecordRepository,
    private val medicalRecordMapper: MedicalRecordMapper
) : MedicalRecordService {

    override fun create(medicalRecordDTO: MedicalRecordDTO): MedicalRecordDTO {
        val medicalRecord = medicalRecordMapper.toEntity(medicalRecordDTO)
        val savedRecord = medicalRecordRepository.save(medicalRecord)
        return medicalRecordMapper.toDto(savedRecord)
    }

    @Transactional(readOnly = true)
    override fun findById(id: UUID): MedicalRecordDTO {
        val medicalRecord = medicalRecordRepository.findById(id)
            .orElseThrow { MedicalRecordNotFoundException(id) }
        return medicalRecordMapper.toDto(medicalRecord)
    }

    @Transactional(readOnly = true)
    override fun findAll(): List<MedicalRecordDTO> {
        val records = medicalRecordRepository.findAll()
        return medicalRecordMapper.toDtos(records)
    }

    @Transactional(readOnly = true)
    override fun findByPatientId(patientId: UUID): List<MedicalRecordDTO> {
        val records = medicalRecordRepository.findByPatientId(patientId)
        return medicalRecordMapper.toDtos(records)
    }

    @Transactional(readOnly = true)
    override fun findByDoctorId(doctorId: UUID): List<MedicalRecordDTO> {
        val records = medicalRecordRepository.findByDoctorId(doctorId)
        return medicalRecordMapper.toDtos(records)
    }

    @Transactional(readOnly = true)
    override fun findByDateRange(start: LocalDateTime, end: LocalDateTime): List<MedicalRecordDTO> {
        val records = medicalRecordRepository.findByRecordDateBetween(start, end)
        return medicalRecordMapper.toDtos(records)
    }

    @Transactional(readOnly = true)
    override fun findByFollowUpRequired(followUpRequired: Boolean): List<MedicalRecordDTO> {
        val records = medicalRecordRepository.findByFollowUpRequired(followUpRequired)
        return medicalRecordMapper.toDtos(records)
    }

    override fun update(id: UUID, medicalRecordDTO: MedicalRecordDTO): MedicalRecordDTO {
        val existingRecord = medicalRecordRepository.findById(id)
            .orElseThrow { MedicalRecordNotFoundException(id) }
        updateRecordFields(existingRecord, medicalRecordDTO)
        val updatedRecord = medicalRecordRepository.save(existingRecord)
        return medicalRecordMapper.toDto(updatedRecord)
    }

    override fun deleteById(id: UUID) {
        if (!medicalRecordRepository.existsById(id)) {
            throw MedicalRecordNotFoundException(id)
        }
        medicalRecordRepository.deleteById(id)
    }

    private fun updateRecordFields(existingRecord: MedicalRecord, dto: MedicalRecordDTO) {
        existingRecord.patientId = dto.patientId
        existingRecord.doctorId = dto.doctorId
        existingRecord.recordDate = dto.recordDate
        dto.diagnosis?.let { existingRecord.diagnosis = it }
        dto.treatment?.let { existingRecord.treatment = it }
        dto.notes?.let { existingRecord.notes = it }
        dto.followUpRequired?.let { existingRecord.followUpRequired = it }
    }
}
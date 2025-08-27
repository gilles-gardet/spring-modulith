package com.ggardet.patient.internal.domain

import com.ggardet.patient.internal.entity.Patient
import com.ggardet.patient.internal.repository.PatientRepository
import com.ggardet.patient.spi.PatientService
import com.ggardet.patient.spi.dto.PatientDTO
import com.ggardet.patient.spi.exception.PatientNotFoundException
import com.ggardet.patient.spi.mapper.PatientMapper
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
class PatientServiceImpl(
    private val patientRepository: PatientRepository,
    private val patientMapper: PatientMapper
) : PatientService {

    override fun create(patientDTO: PatientDTO): PatientDTO {
        val patient = patientMapper.toEntity(patientDTO)
        val savedPatient = patientRepository.save(patient)
        return patientMapper.toDto(savedPatient)
    }

    @Transactional(readOnly = true)
    override fun findById(id: UUID): PatientDTO {
        val patient = patientRepository.findById(id)
            .orElseThrow { PatientNotFoundException(id) }
        return patientMapper.toDto(patient)
    }

    @Transactional(readOnly = true)
    override fun findAll(): List<PatientDTO> {
        val patients = patientRepository.findAll()
        return patientMapper.toDtos(patients)
    }

    @Transactional(readOnly = true)
    override fun findByHospitalId(hospitalId: UUID): List<PatientDTO> {
        val patients = patientRepository.findByHospitalId(hospitalId)
        return patientMapper.toDtos(patients)
    }

    @Transactional(readOnly = true)
    override fun findByServiceId(serviceId: UUID): List<PatientDTO> {
        val patients = patientRepository.findByServiceId(serviceId)
        return patientMapper.toDtos(patients)
    }

    @Transactional(readOnly = true)
    override fun findBySocialSecurityNumber(socialSecurityNumber: String): PatientDTO {
        val patient = patientRepository.findBySocialSecurityNumber(socialSecurityNumber)
            .orElseThrow { PatientNotFoundException(null) }
        return patientMapper.toDto(patient)
    }

    @Transactional(readOnly = true)
    override fun searchByName(name: String): List<PatientDTO> {
        val patients = patientRepository.findByFirstnameContainingIgnoreCaseOrLastnameContainingIgnoreCase(name, name)
        return patientMapper.toDtos(patients)
    }

    override fun update(id: UUID, patientDTO: PatientDTO): PatientDTO {
        val existingPatient = patientRepository.findById(id)
            .orElseThrow { PatientNotFoundException(id) }
        updatePatientFields(existingPatient, patientDTO)
        val updatedPatient = patientRepository.save(existingPatient)
        return patientMapper.toDto(updatedPatient)
    }

    override fun deleteById(id: UUID) {
        if (!patientRepository.existsById(id)) {
            throw PatientNotFoundException(id)
        }
        patientRepository.deleteById(id)
    }

    private fun updatePatientFields(existingPatient: Patient, dto: PatientDTO) {
        dto.firstname.let { existingPatient.firstname = it }
        dto.lastname.let { existingPatient.lastname = it }
        existingPatient.birthDate = dto.birthDate
        dto.socialSecurityNumber?.let { existingPatient.socialSecurityNumber = it }
        dto.phoneNumber?.let { existingPatient.phoneNumber = it }
        dto.email?.let { existingPatient.email = it }
        dto.hospitalId?.let { existingPatient.hospitalId = it }
        dto.serviceId?.let { existingPatient.serviceId = it }
    }
}
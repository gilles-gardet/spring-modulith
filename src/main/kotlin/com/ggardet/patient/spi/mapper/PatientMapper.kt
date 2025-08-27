package com.ggardet.patient.spi.mapper

import com.ggardet.patient.internal.entity.Patient
import com.ggardet.patient.spi.dto.PatientDTO
import org.springframework.stereotype.Component

@Component
class PatientMapper {
    
    fun toDto(patient: Patient): PatientDTO {
        return PatientDTO(
            id = patient.id,
            firstname = patient.firstname,
            lastname = patient.lastname,
            birthDate = patient.birthDate,
            socialSecurityNumber = patient.socialSecurityNumber,
            phoneNumber = patient.phoneNumber,
            email = patient.email,
            hospitalId = patient.hospitalId,
            serviceId = patient.serviceId
        )
    }

    fun toEntity(patientDTO: PatientDTO): Patient {
        return Patient(
            id = patientDTO.id,
            firstname = patientDTO.firstname,
            lastname = patientDTO.lastname,
            birthDate = patientDTO.birthDate,
            socialSecurityNumber = patientDTO.socialSecurityNumber,
            phoneNumber = patientDTO.phoneNumber,
            email = patientDTO.email,
            hospitalId = patientDTO.hospitalId,
            serviceId = patientDTO.serviceId
        )
    }

    fun toDtos(patients: List<Patient>): List<PatientDTO> {
        return patients.map(::toDto)
    }
}
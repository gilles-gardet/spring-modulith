package com.ggardet.doctor.spi.mapper

import com.ggardet.doctor.internal.entity.Doctor
import com.ggardet.doctor.spi.dto.DoctorDTO
import org.springframework.stereotype.Component

@Component
class DoctorMapper {
    
    fun toDto(doctor: Doctor): DoctorDTO {
        return DoctorDTO(
            id = doctor.id,
            firstname = doctor.firstname,
            lastname = doctor.lastname,
            specialty = doctor.specialty,
            licenseNumber = doctor.licenseNumber,
            phoneNumber = doctor.phoneNumber,
            email = doctor.email,
            hospitalId = doctor.hospitalId,
            yearsExperience = doctor.yearsExperience
        )
    }

    fun toEntity(doctorDTO: DoctorDTO): Doctor {
        val doctor = Doctor()
        doctor.id = doctorDTO.id
        doctor.firstname = doctorDTO.firstname
        doctor.lastname = doctorDTO.lastname
        doctor.specialty = doctorDTO.specialty
        doctor.licenseNumber = doctorDTO.licenseNumber
        doctor.phoneNumber = doctorDTO.phoneNumber
        doctor.email = doctorDTO.email
        doctor.hospitalId = doctorDTO.hospitalId
        doctor.yearsExperience = doctorDTO.yearsExperience
        
        return doctor
    }

    fun toDtos(doctors: List<Doctor>): List<DoctorDTO> {
        return doctors.map(::toDto)
    }
}
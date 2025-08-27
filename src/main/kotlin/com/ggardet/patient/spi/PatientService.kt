package com.ggardet.patient.spi

import com.ggardet.patient.spi.dto.PatientDTO
import java.util.*

interface PatientService {
    fun create(patientDTO: PatientDTO): PatientDTO
    fun findById(id: UUID): PatientDTO
    fun findAll(): List<PatientDTO>
    fun findByHospitalId(hospitalId: UUID): List<PatientDTO>
    fun findByServiceId(serviceId: UUID): List<PatientDTO>
    fun findBySocialSecurityNumber(socialSecurityNumber: String): PatientDTO
    fun searchByName(name: String): List<PatientDTO>
    fun update(id: UUID, patientDTO: PatientDTO): PatientDTO
    fun deleteById(id: UUID)
}
package com.ggardet.patient.internal.repository

import com.ggardet.patient.internal.entity.Patient
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface PatientRepository : JpaRepository<Patient, UUID> {
    fun findByHospitalId(hospitalId: UUID): List<Patient>
    fun findByServiceId(serviceId: UUID): List<Patient>
    fun findBySocialSecurityNumber(socialSecurityNumber: String): Optional<Patient>
    fun findByFirstnameContainingIgnoreCaseOrLastnameContainingIgnoreCase(
        firstname: String, 
        lastname: String
    ): List<Patient>
}
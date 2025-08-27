package com.ggardet.doctor.internal.repository

import com.ggardet.doctor.internal.entity.Doctor
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface DoctorRepository : JpaRepository<Doctor, UUID> {
    fun findAllByHospitalId(hospitalId: UUID): List<Doctor>
    
    fun findAllBySpecialty(specialty: String): List<Doctor>
}
package com.ggardet.hospital.internal.repository

import com.ggardet.hospital.internal.entity.Treatment
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface TreatmentRepository : JpaRepository<Treatment, UUID> {
    fun findAllByHospitalId(hospitalId: UUID): List<Treatment>
}
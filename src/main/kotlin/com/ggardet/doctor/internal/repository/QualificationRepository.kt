package com.ggardet.doctor.internal.repository

import com.ggardet.doctor.internal.entity.Qualification
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface QualificationRepository : JpaRepository<Qualification, UUID> {
    fun findAllByDoctorId(doctorId: UUID): List<Qualification>
}
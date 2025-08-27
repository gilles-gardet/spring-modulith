package com.ggardet.doctor.internal.repository

import com.ggardet.doctor.internal.entity.Specialty
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface SpecialtyRepository : JpaRepository<Specialty, UUID> {
    fun findByName(name: String): Optional<Specialty>
}
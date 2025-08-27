package com.ggardet.doctor

import com.ggardet.doctor.internal.entity.Doctor
import com.ggardet.doctor.internal.entity.Qualification
import com.ggardet.doctor.internal.entity.Specialty
import java.util.*

interface DoctorServiceInterface {
    fun createDoctor(doctor: Doctor): Doctor
    
    fun findDoctorById(doctorId: UUID): Doctor
    
    fun findAllByHospital(hospitalId: UUID): List<Doctor>
    
    fun findAllBySpecialty(specialty: String): List<Doctor>
    
    fun deleteDoctor(doctorId: UUID)
    
    fun unlinkDoctorFromHospital(doctor: Doctor)
    
    fun createSpecialty(specialty: Specialty): Specialty
    
    fun findSpecialtyById(specialtyId: UUID): Specialty
    
    fun findAllSpecialties(): List<Specialty>
    
    fun addQualificationToDoctor(doctorId: UUID, qualification: Qualification): Qualification
    
    fun findQualificationsByDoctor(doctorId: UUID): List<Qualification>
    
    fun deleteQualification(qualificationId: UUID)
}
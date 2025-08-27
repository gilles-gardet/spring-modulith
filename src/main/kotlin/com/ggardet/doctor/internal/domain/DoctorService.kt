package com.ggardet.doctor.internal.domain

import com.ggardet.doctor.DoctorServiceInterface
import com.ggardet.doctor.internal.entity.Doctor
import com.ggardet.doctor.internal.entity.Qualification
import com.ggardet.doctor.internal.entity.Specialty
import com.ggardet.doctor.internal.repository.DoctorRepository
import com.ggardet.doctor.internal.repository.QualificationRepository
import com.ggardet.doctor.internal.repository.SpecialtyRepository
import com.ggardet.doctor.spi.exception.DoctorNotFoundException
import com.ggardet.doctor.spi.exception.SpecialtyNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
internal class DoctorService(
    private val doctorRepository: DoctorRepository,
    private val specialtyRepository: SpecialtyRepository,
    private val qualificationRepository: QualificationRepository
) : DoctorServiceInterface {

    override fun createDoctor(doctor: Doctor): Doctor {
        if (doctor.specialty.isNotEmpty()) {
            specialtyRepository.findByName(doctor.specialty)
                .orElseThrow { SpecialtyNotFoundException(doctor.specialty) }
        }
        return doctorRepository.save(doctor)
    }

    override fun findDoctorById(doctorId: UUID): Doctor {
        return doctorRepository.findById(doctorId)
            .orElseThrow { DoctorNotFoundException(doctorId) }
    }

    override fun findAllByHospital(hospitalId: UUID): List<Doctor> {
        return doctorRepository.findAllByHospitalId(hospitalId)
    }

    override fun findAllBySpecialty(specialty: String): List<Doctor> {
        return doctorRepository.findAllBySpecialty(specialty)
    }

    override fun deleteDoctor(doctorId: UUID) {
        doctorRepository.deleteById(doctorId)
    }

    @Transactional
    override fun unlinkDoctorFromHospital(doctor: Doctor) {
        doctor.hospitalId = null
        doctorRepository.save(doctor)
    }

    override fun createSpecialty(specialty: Specialty): Specialty {
        return specialtyRepository.save(specialty)
    }

    override fun findSpecialtyById(specialtyId: UUID): Specialty {
        return specialtyRepository.findById(specialtyId)
            .orElseThrow { SpecialtyNotFoundException(specialtyId) }
    }

    override fun findAllSpecialties(): List<Specialty> {
        return specialtyRepository.findAll()
    }

    override fun addQualificationToDoctor(doctorId: UUID, qualification: Qualification): Qualification {
        val doctor = findDoctorById(doctorId)
        qualification.doctor = doctor
        return qualificationRepository.save(qualification)
    }

    override fun findQualificationsByDoctor(doctorId: UUID): List<Qualification> {
        findDoctorById(doctorId)
        return qualificationRepository.findAllByDoctorId(doctorId)
    }

    override fun deleteQualification(qualificationId: UUID) {
        qualificationRepository.deleteById(qualificationId)
    }
}
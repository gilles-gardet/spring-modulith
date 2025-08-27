package com.ggardet.doctor

import com.ggardet.doctor.spi.dto.DoctorDTO
import com.ggardet.doctor.spi.dto.QualificationDTO
import com.ggardet.doctor.spi.dto.SpecialtyDTO
import com.ggardet.doctor.spi.mapper.DoctorMapper
import com.ggardet.doctor.spi.mapper.QualificationMapper
import com.ggardet.doctor.spi.mapper.SpecialtyMapper
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/doctors")
class DoctorController(
    private val doctorServiceInterface: DoctorServiceInterface,
    private val doctorMapper: DoctorMapper,
    private val specialtyMapper: SpecialtyMapper,
    private val qualificationMapper: QualificationMapper
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createDoctor(@Valid @RequestBody doctorDTO: DoctorDTO): DoctorDTO {
        val response = doctorServiceInterface.createDoctor(doctorMapper.toEntity(doctorDTO))
        return doctorMapper.toDto(response)
    }

    @GetMapping("/{doctorId}")
    fun findDoctorById(@PathVariable doctorId: UUID): DoctorDTO {
        val response = doctorServiceInterface.findDoctorById(doctorId)
        return doctorMapper.toDto(response)
    }

    @GetMapping
    fun findDoctorsByHospital(@RequestParam hospitalId: UUID): List<DoctorDTO> {
        val response = doctorServiceInterface.findAllByHospital(hospitalId)
        return doctorMapper.toDtos(response)
    }

    @GetMapping("/specialty/{specialty}")
    fun findDoctorsBySpecialty(@PathVariable specialty: String): List<DoctorDTO> {
        val response = doctorServiceInterface.findAllBySpecialty(specialty)
        return doctorMapper.toDtos(response)
    }

    @DeleteMapping("/{doctorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteDoctor(@PathVariable doctorId: UUID) {
        doctorServiceInterface.deleteDoctor(doctorId)
    }

    @PostMapping("/specialties")
    @ResponseStatus(HttpStatus.CREATED)
    fun createSpecialty(@Valid @RequestBody specialtyDTO: SpecialtyDTO): SpecialtyDTO {
        val response = doctorServiceInterface.createSpecialty(specialtyMapper.toEntity(specialtyDTO))
        return specialtyMapper.toDto(response)
    }

    @GetMapping("/specialties")
    fun findAllSpecialties(): List<SpecialtyDTO> {
        val response = doctorServiceInterface.findAllSpecialties()
        return specialtyMapper.toDtos(response)
    }

    @GetMapping("/specialties/{specialtyId}")
    fun findSpecialtyById(@PathVariable specialtyId: UUID): SpecialtyDTO {
        val response = doctorServiceInterface.findSpecialtyById(specialtyId)
        return specialtyMapper.toDto(response)
    }

    @PostMapping("/{doctorId}/qualifications")
    @ResponseStatus(HttpStatus.CREATED)
    fun addQualification(
        @PathVariable doctorId: UUID,
        @Valid @RequestBody qualificationDTO: QualificationDTO
    ): QualificationDTO {
        val response = doctorServiceInterface.addQualificationToDoctor(
            doctorId,
            qualificationMapper.toEntity(qualificationDTO)
        )
        return qualificationMapper.toDto(response)
    }

    @GetMapping("/{doctorId}/qualifications")
    fun findQualificationsByDoctor(@PathVariable doctorId: UUID): List<QualificationDTO> {
        val response = doctorServiceInterface.findQualificationsByDoctor(doctorId)
        return qualificationMapper.toDtos(response)
    }

    @DeleteMapping("/qualifications/{qualificationId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteQualification(@PathVariable qualificationId: UUID) {
        doctorServiceInterface.deleteQualification(qualificationId)
    }
}
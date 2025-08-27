package com.ggardet.patient

import com.ggardet.patient.spi.dto.PatientDTO
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/patients")
class PatientController(
    private val patientServiceInterface: PatientServiceInterface
) {

    @PostMapping
    fun create(@Valid @RequestBody patientDTO: PatientDTO): ResponseEntity<PatientDTO> {
        val createdPatient = patientServiceInterface.createPatient(patientDTO)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPatient)
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: UUID): ResponseEntity<PatientDTO> {
        val patient = patientServiceInterface.findPatientById(id)
        return ResponseEntity.ok(patient)
    }

    @GetMapping
    fun findAll(): ResponseEntity<List<PatientDTO>> {
        val patients = patientServiceInterface.findAllPatients()
        return ResponseEntity.ok(patients)
    }

    @GetMapping("/hospital/{hospitalId}")
    fun findByHospitalId(@PathVariable hospitalId: UUID): ResponseEntity<List<PatientDTO>> {
        val patients = patientServiceInterface.findPatientsByHospitalId(hospitalId)
        return ResponseEntity.ok(patients)
    }

    @GetMapping("/ssn/{socialSecurityNumber}")
    fun findBySocialSecurityNumber(@PathVariable socialSecurityNumber: String): ResponseEntity<PatientDTO> {
        val patient = patientServiceInterface.findPatientBySocialSecurityNumber(socialSecurityNumber)
        return ResponseEntity.ok(patient)
    }
}
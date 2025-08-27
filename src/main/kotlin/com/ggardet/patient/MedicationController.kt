package com.ggardet.patient

import com.ggardet.patient.spi.dto.MedicationDTO
import jakarta.validation.Valid
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate
import java.util.*

@RestController
@RequestMapping("/medications")
class MedicationController(
    private val patientServiceInterface: PatientServiceInterface
) {

    @PostMapping
    fun create(@Valid @RequestBody medicationDTO: MedicationDTO): ResponseEntity<MedicationDTO> {
        val createdMedication = patientServiceInterface.createMedication(medicationDTO)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMedication)
    }

    @GetMapping("/patient/{patientId}")
    fun findByPatientId(@PathVariable patientId: UUID): ResponseEntity<List<MedicationDTO>> {
        val medications = patientServiceInterface.findMedicationsByPatientId(patientId)
        return ResponseEntity.ok(medications)
    }

    @GetMapping("/active")
    fun findActiveMedications(): ResponseEntity<List<MedicationDTO>> {
        val medications = patientServiceInterface.findActiveMedications()
        return ResponseEntity.ok(medications)
    }

    @GetMapping("/active-after")
    fun findActiveMedicationsAfterDate(
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) date: LocalDate
    ): ResponseEntity<List<MedicationDTO>> {
        val medications = patientServiceInterface.findActiveMedicationsAfterDate(date)
        return ResponseEntity.ok(medications)
    }
}
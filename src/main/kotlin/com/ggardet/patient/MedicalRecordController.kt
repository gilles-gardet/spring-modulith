package com.ggardet.patient

import com.ggardet.patient.spi.dto.MedicalRecordDTO
import jakarta.validation.Valid
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
import java.util.*

@RestController
@RequestMapping("/medical-records")
class MedicalRecordController(
    private val patientServiceInterface: PatientServiceInterface
) {

    @PostMapping
    fun create(@Valid @RequestBody medicalRecordDTO: MedicalRecordDTO): ResponseEntity<MedicalRecordDTO> {
        val createdRecord = patientServiceInterface.createMedicalRecord(medicalRecordDTO)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRecord)
    }

    @GetMapping("/patient/{patientId}")
    fun findByPatientId(@PathVariable patientId: UUID): ResponseEntity<List<MedicalRecordDTO>> {
        val records = patientServiceInterface.findMedicalRecordsByPatientId(patientId)
        return ResponseEntity.ok(records)
    }

    @GetMapping("/date-range")
    fun findByDateRange(
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) start: LocalDateTime,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) end: LocalDateTime
    ): ResponseEntity<List<MedicalRecordDTO>> {
        val records = patientServiceInterface.findMedicalRecordsByDateRange(start, end)
        return ResponseEntity.ok(records)
    }
}
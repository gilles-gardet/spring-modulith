package com.ggardet.patient;

import com.ggardet.patient.spi.dto.MedicalRecordDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/medical-records")
@RequiredArgsConstructor
public class MedicalRecordController {
    private final PatientServiceInterface patientServiceInterface;

    @PostMapping
    public ResponseEntity<MedicalRecordDTO> create(@Valid @RequestBody final MedicalRecordDTO medicalRecordDTO) {
        final var createdRecord = patientServiceInterface.createMedicalRecord(medicalRecordDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRecord);
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<MedicalRecordDTO>> findByPatientId(@PathVariable final UUID patientId) {
        final var records = patientServiceInterface.findMedicalRecordsByPatientId(patientId);
        return ResponseEntity.ok(records);
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<MedicalRecordDTO>> findByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) final LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) final LocalDateTime end) {
        final var records = patientServiceInterface.findMedicalRecordsByDateRange(start, end);
        return ResponseEntity.ok(records);
    }
}
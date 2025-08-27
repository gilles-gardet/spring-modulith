package com.ggardet.patient;

import com.ggardet.patient.spi.dto.MedicationDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/medications")
@RequiredArgsConstructor
public class MedicationController {
    private final PatientServiceInterface patientServiceInterface;

    @PostMapping
    public ResponseEntity<MedicationDTO> create(@Valid @RequestBody final MedicationDTO medicationDTO) {
        final var createdMedication = patientServiceInterface.createMedication(medicationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMedication);
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<MedicationDTO>> findByPatientId(@PathVariable final UUID patientId) {
        final var medications = patientServiceInterface.findMedicationsByPatientId(patientId);
        return ResponseEntity.ok(medications);
    }

    @GetMapping("/active")
    public ResponseEntity<List<MedicationDTO>> findActiveMedications() {
        final var medications = patientServiceInterface.findActiveMedications();
        return ResponseEntity.ok(medications);
    }

    @GetMapping("/active-after")
    public ResponseEntity<List<MedicationDTO>> findActiveMedicationsAfterDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate date) {
        final var medications = patientServiceInterface.findActiveMedicationsAfterDate(date);
        return ResponseEntity.ok(medications);
    }
}
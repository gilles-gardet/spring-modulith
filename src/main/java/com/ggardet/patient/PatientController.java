package com.ggardet.patient;

import com.ggardet.patient.spi.dto.PatientDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {
    private final PatientServiceInterface patientServiceInterface;

    @PostMapping
    public ResponseEntity<PatientDTO> create(@Valid @RequestBody final PatientDTO patientDTO) {
        final var createdPatient = patientServiceInterface.createPatient(patientDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPatient);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> findById(@PathVariable final UUID id) {
        final var patient = patientServiceInterface.findPatientById(id);
        return ResponseEntity.ok(patient);
    }

    @GetMapping
    public ResponseEntity<List<PatientDTO>> findAll() {
        final var patients = patientServiceInterface.findAllPatients();
        return ResponseEntity.ok(patients);
    }

    @GetMapping("/hospital/{hospitalId}")
    public ResponseEntity<List<PatientDTO>> findByHospitalId(@PathVariable final UUID hospitalId) {
        final var patients = patientServiceInterface.findPatientsByHospitalId(hospitalId);
        return ResponseEntity.ok(patients);
    }

    @GetMapping("/ssn/{socialSecurityNumber}")
    public ResponseEntity<PatientDTO> findBySocialSecurityNumber(@PathVariable final String socialSecurityNumber) {
        final var patient = patientServiceInterface.findPatientBySocialSecurityNumber(socialSecurityNumber);
        return ResponseEntity.ok(patient);
    }
}
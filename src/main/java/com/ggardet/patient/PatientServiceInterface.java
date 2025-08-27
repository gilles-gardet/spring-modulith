package com.ggardet.patient;

import com.ggardet.patient.spi.dto.MedicalRecordDTO;
import com.ggardet.patient.spi.dto.MedicationDTO;
import com.ggardet.patient.spi.dto.PatientDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface PatientServiceInterface {
    PatientDTO createPatient(final PatientDTO patientDTO);

    PatientDTO findPatientById(final UUID id);

    List<PatientDTO> findAllPatients();

    List<PatientDTO> findPatientsByHospitalId(final UUID hospitalId);

    PatientDTO findPatientBySocialSecurityNumber(final String socialSecurityNumber);

    MedicalRecordDTO createMedicalRecord(final MedicalRecordDTO medicalRecordDTO);

    List<MedicalRecordDTO> findMedicalRecordsByPatientId(final UUID patientId);

    List<MedicalRecordDTO> findMedicalRecordsByDateRange(final LocalDateTime start, final LocalDateTime end);

    MedicationDTO createMedication(final MedicationDTO medicationDTO);

    List<MedicationDTO> findMedicationsByPatientId(final UUID patientId);

    List<MedicationDTO> findActiveMedications();

    List<MedicationDTO> findActiveMedicationsAfterDate(final LocalDate date);
}

package com.ggardet.patient.internal.domain;

import com.ggardet.patient.PatientServiceInterface;
import com.ggardet.patient.spi.MedicalRecordService;
import com.ggardet.patient.spi.MedicationService;
import com.ggardet.patient.spi.PatientService;
import com.ggardet.patient.spi.dto.MedicalRecordDTO;
import com.ggardet.patient.spi.dto.MedicationDTO;
import com.ggardet.patient.spi.dto.PatientDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
class PatientServiceInterfaceImpl implements PatientServiceInterface {
    private final PatientService patientService;
    private final MedicalRecordService medicalRecordService;
    private final MedicationService medicationService;

    @Override
    public PatientDTO createPatient(final PatientDTO patientDTO) {
        return patientService.create(patientDTO);
    }

    @Override
    public PatientDTO findPatientById(final UUID id) {
        return patientService.findById(id);
    }

    @Override
    public List<PatientDTO> findAllPatients() {
        return patientService.findAll();
    }

    @Override
    public List<PatientDTO> findPatientsByHospitalId(final UUID hospitalId) {
        return patientService.findByHospitalId(hospitalId);
    }

    @Override
    public PatientDTO findPatientBySocialSecurityNumber(final String socialSecurityNumber) {
        return patientService.findBySocialSecurityNumber(socialSecurityNumber);
    }

    @Override
    public MedicalRecordDTO createMedicalRecord(final MedicalRecordDTO medicalRecordDTO) {
        return medicalRecordService.create(medicalRecordDTO);
    }

    @Override
    public List<MedicalRecordDTO> findMedicalRecordsByPatientId(final UUID patientId) {
        return medicalRecordService.findByPatientId(patientId);
    }

    @Override
    public List<MedicalRecordDTO> findMedicalRecordsByDateRange(final LocalDateTime start, final LocalDateTime end) {
        return medicalRecordService.findByDateRange(start, end);
    }

    @Override
    public MedicationDTO createMedication(final MedicationDTO medicationDTO) {
        return medicationService.create(medicationDTO);
    }

    @Override
    public List<MedicationDTO> findMedicationsByPatientId(final UUID patientId) {
        return medicationService.findByPatientId(patientId);
    }

    @Override
    public List<MedicationDTO> findActiveMedications() {
        return medicationService.findActiveMedications();
    }

    @Override
    public List<MedicationDTO> findActiveMedicationsAfterDate(final LocalDate date) {
        return medicationService.findActiveMedicationsAfterDate(date);
    }
}
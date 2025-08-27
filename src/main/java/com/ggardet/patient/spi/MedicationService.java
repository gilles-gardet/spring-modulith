package com.ggardet.patient.spi;

import com.ggardet.patient.spi.dto.MedicationDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface MedicationService {
    MedicationDTO create(final MedicationDTO medicationDTO);
    MedicationDTO findById(final UUID id);
    List<MedicationDTO> findAll();
    List<MedicationDTO> findByPatientId(final UUID patientId);
    List<MedicationDTO> findByPrescribingDoctorId(final UUID doctorId);
    List<MedicationDTO> findActiveMedications();
    List<MedicationDTO> findActiveMedicationsAfterDate(final LocalDate date);
    MedicationDTO update(final UUID id, final MedicationDTO medicationDTO);
    void deleteById(final UUID id);
}
package com.ggardet.patient.spi;

import com.ggardet.patient.spi.dto.MedicalRecordDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface MedicalRecordService {
    MedicalRecordDTO create(final MedicalRecordDTO medicalRecordDTO);
    MedicalRecordDTO findById(final UUID id);
    List<MedicalRecordDTO> findAll();
    List<MedicalRecordDTO> findByPatientId(final UUID patientId);
    List<MedicalRecordDTO> findByDoctorId(final UUID doctorId);
    List<MedicalRecordDTO> findByDateRange(final LocalDateTime start, final LocalDateTime end);
    List<MedicalRecordDTO> findByFollowUpRequired(final Boolean followUpRequired);
    MedicalRecordDTO update(final UUID id, final MedicalRecordDTO medicalRecordDTO);
    void deleteById(final UUID id);
}
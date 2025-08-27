package com.ggardet.patient.internal.repository;

import com.ggardet.patient.internal.entity.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, UUID> {
    List<MedicalRecord> findByPatientId(final UUID patientId);
    List<MedicalRecord> findByDoctorId(final UUID doctorId);
    List<MedicalRecord> findByRecordDateBetween(final LocalDateTime start, final LocalDateTime end);
    List<MedicalRecord> findByFollowUpRequired(final Boolean followUpRequired);
}
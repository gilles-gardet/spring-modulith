package com.ggardet.patient.internal.repository;

import com.ggardet.patient.internal.entity.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface MedicationRepository extends JpaRepository<Medication, UUID> {
    List<Medication> findByPatientId(final UUID patientId);
    List<Medication> findByPrescribingDoctorId(final UUID doctorId);
    List<Medication> findByEndDateIsNull();
    List<Medication> findByEndDateAfter(final LocalDate date);
}
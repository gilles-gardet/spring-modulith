package com.ggardet.patient.internal.repository;

import com.ggardet.patient.internal.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PatientRepository extends JpaRepository<Patient, UUID> {
    List<Patient> findByHospitalId(final UUID hospitalId);
    List<Patient> findByServiceId(final UUID serviceId);
    Optional<Patient> findBySocialSecurityNumber(final String socialSecurityNumber);
    List<Patient> findByFirstnameContainingIgnoreCaseOrLastnameContainingIgnoreCase(final String firstname, final String lastname);
}
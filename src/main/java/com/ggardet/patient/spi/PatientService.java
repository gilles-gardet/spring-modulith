package com.ggardet.patient.spi;

import com.ggardet.patient.spi.dto.PatientDTO;

import java.util.List;
import java.util.UUID;

public interface PatientService {
    PatientDTO create(final PatientDTO patientDTO);
    PatientDTO findById(final UUID id);
    List<PatientDTO> findAll();
    List<PatientDTO> findByHospitalId(final UUID hospitalId);
    List<PatientDTO> findByServiceId(final UUID serviceId);
    PatientDTO findBySocialSecurityNumber(final String socialSecurityNumber);
    List<PatientDTO> searchByName(final String name);
    PatientDTO update(final UUID id, final PatientDTO patientDTO);
    void deleteById(final UUID id);
}
package com.ggardet.patient.internal.domain;

import com.ggardet.patient.internal.entity.Patient;
import com.ggardet.patient.internal.repository.PatientRepository;
import com.ggardet.patient.spi.PatientService;
import com.ggardet.patient.spi.dto.PatientDTO;
import com.ggardet.patient.spi.exception.PatientNotFoundException;
import com.ggardet.patient.spi.mapper.PatientMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    @Override
    public PatientDTO create(final PatientDTO patientDTO) {
        final var patient = patientMapper.toEntity(patientDTO);
        final var savedPatient = patientRepository.save(patient);
        return patientMapper.toDto(savedPatient);
    }

    @Override
    @Transactional(readOnly = true)
    public PatientDTO findById(final UUID id) {
        final var patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException(id));
        return patientMapper.toDto(patient);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PatientDTO> findAll() {
        final var patients = patientRepository.findAll();
        return patientMapper.toDtos(patients);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PatientDTO> findByHospitalId(final UUID hospitalId) {
        final var patients = patientRepository.findByHospitalId(hospitalId);
        return patientMapper.toDtos(patients);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PatientDTO> findByServiceId(final UUID serviceId) {
        final var patients = patientRepository.findByServiceId(serviceId);
        return patientMapper.toDtos(patients);
    }

    @Override
    @Transactional(readOnly = true)
    public PatientDTO findBySocialSecurityNumber(final String socialSecurityNumber) {
        final var patient = patientRepository.findBySocialSecurityNumber(socialSecurityNumber)
                .orElseThrow(() -> new PatientNotFoundException(null));
        return patientMapper.toDto(patient);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PatientDTO> searchByName(final String name) {
        final var patients = patientRepository.findByFirstnameContainingIgnoreCaseOrLastnameContainingIgnoreCase(name, name);
        return patientMapper.toDtos(patients);
    }

    @Override
    public PatientDTO update(final UUID id, final PatientDTO patientDTO) {
        final var existingPatient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException(id));
        updatePatientFields(existingPatient, patientDTO);
        final var updatedPatient = patientRepository.save(existingPatient);
        return patientMapper.toDto(updatedPatient);
    }

    @Override
    public void deleteById(final UUID id) {
        if (!patientRepository.existsById(id)) {
            throw new PatientNotFoundException(id);
        }
        patientRepository.deleteById(id);
    }

    private void updatePatientFields(final Patient existingPatient, final PatientDTO dto) {
        if (Objects.nonNull(dto.firstname())) {
            existingPatient.setFirstname(dto.firstname());
        }
        if (Objects.nonNull(dto.lastname())) {
            existingPatient.setLastname(dto.lastname());
        }
        if (Objects.nonNull(dto.birthDate())) {
            existingPatient.setBirthDate(dto.birthDate());
        }
        if (Objects.nonNull(dto.socialSecurityNumber())) {
            existingPatient.setSocialSecurityNumber(dto.socialSecurityNumber());
        }
        if (Objects.nonNull(dto.phoneNumber())) {
            existingPatient.setPhoneNumber(dto.phoneNumber());
        }
        if (Objects.nonNull(dto.email())) {
            existingPatient.setEmail(dto.email());
        }
        if (Objects.nonNull(dto.hospitalId())) {
            existingPatient.setHospitalId(dto.hospitalId());
        }
        if (Objects.nonNull(dto.serviceId())) {
            existingPatient.setServiceId(dto.serviceId());
        }
    }
}
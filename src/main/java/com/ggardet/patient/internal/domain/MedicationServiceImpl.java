package com.ggardet.patient.internal.domain;

import com.ggardet.patient.internal.entity.Medication;
import com.ggardet.patient.internal.repository.MedicationRepository;
import com.ggardet.patient.spi.MedicationService;
import com.ggardet.patient.spi.dto.MedicationDTO;
import com.ggardet.patient.spi.exception.MedicationNotFoundException;
import com.ggardet.patient.spi.mapper.MedicationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class MedicationServiceImpl implements MedicationService {
    private final MedicationRepository medicationRepository;
    private final MedicationMapper medicationMapper;

    @Override
    public MedicationDTO create(final MedicationDTO medicationDTO) {
        final var medication = medicationMapper.toEntity(medicationDTO);
        final var savedMedication = medicationRepository.save(medication);
        return medicationMapper.toDto(savedMedication);
    }

    @Override
    @Transactional(readOnly = true)
    public MedicationDTO findById(final UUID id) {
        final var medication = medicationRepository.findById(id)
                .orElseThrow(() -> new MedicationNotFoundException(id));
        return medicationMapper.toDto(medication);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MedicationDTO> findAll() {
        final var medications = medicationRepository.findAll();
        return medicationMapper.toDtos(medications);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MedicationDTO> findByPatientId(final UUID patientId) {
        final var medications = medicationRepository.findByPatientId(patientId);
        return medicationMapper.toDtos(medications);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MedicationDTO> findByPrescribingDoctorId(final UUID doctorId) {
        final var medications = medicationRepository.findByPrescribingDoctorId(doctorId);
        return medicationMapper.toDtos(medications);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MedicationDTO> findActiveMedications() {
        final var medications = medicationRepository.findByEndDateIsNull();
        return medicationMapper.toDtos(medications);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MedicationDTO> findActiveMedicationsAfterDate(final LocalDate date) {
        final var medications = medicationRepository.findByEndDateAfter(date);
        return medicationMapper.toDtos(medications);
    }

    @Override
    public MedicationDTO update(final UUID id, final MedicationDTO medicationDTO) {
        final var existingMedication = medicationRepository.findById(id)
                .orElseThrow(() -> new MedicationNotFoundException(id));
        updateMedicationFields(existingMedication, medicationDTO);
        final var updatedMedication = medicationRepository.save(existingMedication);
        return medicationMapper.toDto(updatedMedication);
    }

    @Override
    public void deleteById(final UUID id) {
        if (!medicationRepository.existsById(id)) {
            throw new MedicationNotFoundException(id);
        }
        medicationRepository.deleteById(id);
    }

    private void updateMedicationFields(final Medication existingMedication, final MedicationDTO dto) {
        if (Objects.nonNull(dto.patientId())) {
            existingMedication.setPatientId(dto.patientId());
        }
        if (Objects.nonNull(dto.name())) {
            existingMedication.setName(dto.name());
        }
        if (Objects.nonNull(dto.dosage())) {
            existingMedication.setDosage(dto.dosage());
        }
        if (Objects.nonNull(dto.frequency())) {
            existingMedication.setFrequency(dto.frequency());
        }
        if (Objects.nonNull(dto.startDate())) {
            existingMedication.setStartDate(dto.startDate());
        }
        if (Objects.nonNull(dto.endDate())) {
            existingMedication.setEndDate(dto.endDate());
        }
        if (Objects.nonNull(dto.prescribingDoctorId())) {
            existingMedication.setPrescribingDoctorId(dto.prescribingDoctorId());
        }
        if (Objects.nonNull(dto.instructions())) {
            existingMedication.setInstructions(dto.instructions());
        }
    }
}
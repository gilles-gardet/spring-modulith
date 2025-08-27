package com.ggardet.patient.internal.domain;

import com.ggardet.patient.internal.entity.MedicalRecord;
import com.ggardet.patient.internal.repository.MedicalRecordRepository;
import com.ggardet.patient.spi.MedicalRecordService;
import com.ggardet.patient.spi.dto.MedicalRecordDTO;
import com.ggardet.patient.spi.exception.MedicalRecordNotFoundException;
import com.ggardet.patient.spi.mapper.MedicalRecordMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class MedicalRecordServiceImpl implements MedicalRecordService {
    private final MedicalRecordRepository medicalRecordRepository;
    private final MedicalRecordMapper medicalRecordMapper;

    @Override
    public MedicalRecordDTO create(final MedicalRecordDTO medicalRecordDTO) {
        final var medicalRecord = medicalRecordMapper.toEntity(medicalRecordDTO);
        final var savedRecord = medicalRecordRepository.save(medicalRecord);
        return medicalRecordMapper.toDto(savedRecord);
    }

    @Override
    @Transactional(readOnly = true)
    public MedicalRecordDTO findById(final UUID id) {
        final var medicalRecord = medicalRecordRepository.findById(id)
                .orElseThrow(() -> new MedicalRecordNotFoundException(id));
        return medicalRecordMapper.toDto(medicalRecord);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MedicalRecordDTO> findAll() {
        final var records = medicalRecordRepository.findAll();
        return medicalRecordMapper.toDtos(records);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MedicalRecordDTO> findByPatientId(final UUID patientId) {
        final var records = medicalRecordRepository.findByPatientId(patientId);
        return medicalRecordMapper.toDtos(records);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MedicalRecordDTO> findByDoctorId(final UUID doctorId) {
        final var records = medicalRecordRepository.findByDoctorId(doctorId);
        return medicalRecordMapper.toDtos(records);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MedicalRecordDTO> findByDateRange(final LocalDateTime start, final LocalDateTime end) {
        final var records = medicalRecordRepository.findByRecordDateBetween(start, end);
        return medicalRecordMapper.toDtos(records);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MedicalRecordDTO> findByFollowUpRequired(final Boolean followUpRequired) {
        final var records = medicalRecordRepository.findByFollowUpRequired(followUpRequired);
        return medicalRecordMapper.toDtos(records);
    }

    @Override
    public MedicalRecordDTO update(final UUID id, final MedicalRecordDTO medicalRecordDTO) {
        final var existingRecord = medicalRecordRepository.findById(id)
                .orElseThrow(() -> new MedicalRecordNotFoundException(id));
        updateRecordFields(existingRecord, medicalRecordDTO);
        final var updatedRecord = medicalRecordRepository.save(existingRecord);
        return medicalRecordMapper.toDto(updatedRecord);
    }

    @Override
    public void deleteById(final UUID id) {
        if (!medicalRecordRepository.existsById(id)) {
            throw new MedicalRecordNotFoundException(id);
        }
        medicalRecordRepository.deleteById(id);
    }

    private void updateRecordFields(final MedicalRecord existingRecord, final MedicalRecordDTO dto) {
        if (Objects.nonNull(dto.patientId())) {
            existingRecord.setPatientId(dto.patientId());
        }
        if (Objects.nonNull(dto.doctorId())) {
            existingRecord.setDoctorId(dto.doctorId());
        }
        if (Objects.nonNull(dto.recordDate())) {
            existingRecord.setRecordDate(dto.recordDate());
        }
        if (Objects.nonNull(dto.diagnosis())) {
            existingRecord.setDiagnosis(dto.diagnosis());
        }
        if (Objects.nonNull(dto.treatment())) {
            existingRecord.setTreatment(dto.treatment());
        }
        if (Objects.nonNull(dto.notes())) {
            existingRecord.setNotes(dto.notes());
        }
        if (Objects.nonNull(dto.followUpRequired())) {
            existingRecord.setFollowUpRequired(dto.followUpRequired());
        }
    }
}
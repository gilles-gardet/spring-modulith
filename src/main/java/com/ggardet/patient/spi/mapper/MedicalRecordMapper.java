package com.ggardet.patient.spi.mapper;

import com.ggardet.patient.internal.entity.MedicalRecord;
import com.ggardet.patient.spi.dto.MedicalRecordDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MedicalRecordMapper {
    MedicalRecordDTO toDto(final MedicalRecord medicalRecord);
    MedicalRecord toEntity(final MedicalRecordDTO medicalRecordDTO);
    List<MedicalRecordDTO> toDtos(final List<MedicalRecord> medicalRecords);
}
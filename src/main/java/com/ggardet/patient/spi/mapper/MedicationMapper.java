package com.ggardet.patient.spi.mapper;

import com.ggardet.patient.internal.entity.Medication;
import com.ggardet.patient.spi.dto.MedicationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MedicationMapper {
    MedicationDTO toDto(final Medication medication);
    Medication toEntity(final MedicationDTO medicationDTO);
    List<MedicationDTO> toDtos(final List<Medication> medications);
}
package com.ggardet.patient.spi.mapper;

import com.ggardet.patient.internal.entity.Patient;
import com.ggardet.patient.spi.dto.PatientDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PatientMapper {
    PatientDTO toDto(final Patient patient);
    Patient toEntity(final PatientDTO patientDTO);
    List<PatientDTO> toDtos(final List<Patient> patients);
}
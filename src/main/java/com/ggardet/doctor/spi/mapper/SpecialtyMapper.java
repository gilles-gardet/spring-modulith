package com.ggardet.doctor.spi.mapper;

import com.ggardet.doctor.internal.entity.Specialty;
import com.ggardet.doctor.spi.dto.SpecialtyDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SpecialtyMapper {
    SpecialtyDTO toDto(final Specialty specialty);

    Specialty toEntity(final SpecialtyDTO specialtyDTO);

    List<SpecialtyDTO> toDtos(final List<Specialty> specialties);
}

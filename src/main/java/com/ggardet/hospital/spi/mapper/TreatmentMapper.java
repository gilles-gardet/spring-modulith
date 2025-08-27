package com.ggardet.hospital.spi.mapper;

import com.ggardet.hospital.internal.entity.Treatment;
import com.ggardet.hospital.spi.dto.TreatmentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TreatmentMapper {
    TreatmentDTO toDto(final Treatment treatment);

    Treatment toEntity(final TreatmentDTO treatmentDTO);

    List<TreatmentDTO> toDtos(final List<Treatment> treatments);
}

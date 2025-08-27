package com.ggardet.doctor.spi.mapper;

import com.ggardet.doctor.internal.entity.Qualification;
import com.ggardet.doctor.spi.dto.QualificationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface QualificationMapper {
    QualificationDTO toDto(final Qualification qualification);

    Qualification toEntity(final QualificationDTO qualificationDTO);

    List<QualificationDTO> toDtos(final List<Qualification> qualifications);
}

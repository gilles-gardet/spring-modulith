package com.ggardet.establishment.spi.mapper;

import com.ggardet.establishment.internal.entity.Establishment;
import com.ggardet.establishment.spi.dto.EstablishmentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EstablishmentMapper {
    @Mapping(target = "employees", ignore = true)
    @Mapping(target = "activities", ignore = true)
    Establishment toEntity(final EstablishmentDTO establishmentDTO);

    EstablishmentDTO toDto(final Establishment establishment);

    List<EstablishmentDTO> toDtos(final List<Establishment> establishments);
}

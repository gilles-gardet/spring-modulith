package com.ggardet.hospital.spi.mapper;

import com.ggardet.hospital.internal.entity.Hospital;
import com.ggardet.hospital.spi.dto.HospitalDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface HospitalMapper {
    HospitalDTO toDto(final Hospital hospital);

    Hospital toEntity(final HospitalDTO hospitalDTO);
}

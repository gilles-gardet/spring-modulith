package com.ggardet.doctor.spi.mapper;

import com.ggardet.doctor.internal.entity.Doctor;
import com.ggardet.doctor.spi.dto.DoctorDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DoctorMapper {
    DoctorDTO toDto(final Doctor doctor);

    Doctor toEntity(final DoctorDTO doctorDTO);

    List<DoctorDTO> toDtos(final List<Doctor> doctors);
}

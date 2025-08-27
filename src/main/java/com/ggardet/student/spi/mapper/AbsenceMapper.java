package com.ggardet.student.spi.mapper;

import com.ggardet.student.internal.entity.Absence;
import com.ggardet.student.spi.dto.AbsenceDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AbsenceMapper {
    @Mapping(target = "date", source = "absenceDTO.date",
            dateFormat = "yyyy-MM-dd")
    @Mapping(target = "student", ignore = true)
    @Mapping(target = "classroomId", ignore = true)
    Absence toEntity(final AbsenceDTO absenceDTO);

    @Mapping(target = "date", source = "absence.date",
            dateFormat = "yyyy-MM-dd")
    @Mapping(target = "studentId", ignore = true)
    AbsenceDTO toDto(final Absence absence);

    @Mapping(target = "date", source = "absences.date",
            dateFormat = "yyyy-MM-dd")
    List<AbsenceDTO> toDtos(final List<Absence> absences);
}

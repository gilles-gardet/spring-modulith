package com.ggardet.classroom.spi.mapper;

import com.ggardet.classroom.internal.entity.Classroom;
import com.ggardet.classroom.spi.dto.ClassroomDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClassroomMapper {
    @Mapping(target = "plannings", ignore = true)
    Classroom toEntity(final ClassroomDTO classroomDTO);

    ClassroomDTO toDto(final Classroom classroom);

    List<ClassroomDTO> toDtos(final List<Classroom> classrooms);
}

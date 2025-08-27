package com.ggardet.student.spi.mapper;

import com.ggardet.student.internal.entity.Student;
import com.ggardet.student.spi.dto.StudentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    @Mapping(target = "birthdate", source = "studentDTO.birthdate",
            dateFormat = "yyyy-MM-dd")
    @Mapping(target = "absences", ignore = true)
    @Mapping(target = "rates", ignore = true)
    Student toEntity(final StudentDTO studentDTO);

    @Mapping(target = "birthdate", source = "student.birthdate",
            dateFormat = "yyyy-MM-dd")
    StudentDTO toDto(final Student student);

    @Mapping(target = "birthdate", source = "students.birthdate",
            dateFormat = "yyyy-MM-dd")
    List<StudentDTO> toDtos(final List<Student> students);
}

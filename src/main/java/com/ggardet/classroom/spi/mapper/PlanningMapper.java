package com.ggardet.classroom.spi.mapper;

import com.ggardet.classroom.internal.entity.Planning;
import com.ggardet.classroom.spi.dto.PlanningDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PlanningMapper {
    @Mapping(target = "classroom", ignore = true)
    Planning toEntity(final PlanningDTO planningDTO);

    @Mapping(target = "classroomId", ignore = true)
    PlanningDTO toDto(final Planning planning);

    List<PlanningDTO> toDtos(final List<Planning> plannings);
}

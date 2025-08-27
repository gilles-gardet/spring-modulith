package com.ggardet.student.spi.mapper;

import com.ggardet.student.internal.entity.Rate;
import com.ggardet.student.spi.dto.RateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RateMapper {
    @Mapping(target = "date", source = "rateDTO.date",
            dateFormat = "yyyy-MM-dd")
    @Mapping(target = "student", ignore = true)
    @Mapping(target = "classroomId", ignore = true)
    Rate toEntity(final RateDTO rateDTO);

    @Mapping(target = "date", source = "rate.date",
            dateFormat = "yyyy-MM-dd")
    @Mapping(target = "studentId", ignore = true)
    RateDTO toDto(final Rate rate);

    @Mapping(target = "date", source = "rates.date",
            dateFormat = "yyyy-MM-dd")
    List<RateDTO> toDtos(final List<Rate> rate);
}

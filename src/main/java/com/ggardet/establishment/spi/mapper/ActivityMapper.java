package com.ggardet.establishment.spi.mapper;

import com.ggardet.establishment.internal.entity.Activity;
import com.ggardet.establishment.spi.dto.ActivityDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ActivityMapper {
    @Mapping(target = "establishment", ignore = true)
    Activity toEntity(final ActivityDTO activityDTO);

    @Mapping(target = "establishmentId", ignore = true)
    ActivityDTO toDto(final Activity activity);

    List<ActivityDTO> toDtos(final List<Activity> activities);
}

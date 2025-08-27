package com.ggardet.classroom.spi.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;

import java.util.UUID;

@Builder(toBuilder = true)
public record ClassroomDTO(
        UUID id,

        @NotBlank
        @Size(max = 255)
        String name,

        @NotNull
        UUID establishmentId,

        @NotBlank
        @Size(max = 255)
        String level,

        @NotNull
        @Min(value = 1)
        @Max(value = 35)
        Integer maxStudentsCapacity
) {
}

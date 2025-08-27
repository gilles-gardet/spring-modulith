package com.ggardet.classroom.spi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.util.UUID;

@Builder(toBuilder = true)
public record PlanningDTO(
        UUID id,

        @NotBlank
        @Size(max = 10)
        String day,

        @NotNull
        @Size(max = 5)
        String beginHour,

        @NotBlank
        @Size(max = 5)
        String endHour,

        @NotBlank
        @Size(max = 255)
        String subject,

        @NotNull
        UUID teacherId,

        @NotBlank
        @Size(max = 255)
        String location,

        @NotNull
        UUID classroomId
) {
}

package com.ggardet.student.spi.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.util.UUID;

@Builder(toBuilder = true)
public record AbsenceDTO(
        UUID id,

        @NotBlank
        UUID studentId,

        @NotBlank
        String date
) {
}

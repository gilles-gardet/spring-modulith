package com.ggardet.student.spi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.util.UUID;

@Builder(toBuilder = true)
public record RateDTO(
        UUID id,

        @NotBlank
        UUID studentId,

        @NotBlank
        @Size(max = 255)
        String subject,

        @NotBlank
        String date,

        @NotBlank
        @PositiveOrZero
        Float value
) {
}

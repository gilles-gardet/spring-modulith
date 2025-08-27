package com.ggardet.hospital.spi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record TreatmentDTO(
        UUID id,

        @NotBlank String name,

        String description,

        @Positive Integer durationMinutes,

        String protocol
) {
}

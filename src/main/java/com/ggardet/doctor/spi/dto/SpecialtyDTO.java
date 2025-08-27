package com.ggardet.doctor.spi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record SpecialtyDTO(
        UUID id,

        @NotBlank String name,

        String description,

        @Positive Integer requiredYearsTraining
) {
}

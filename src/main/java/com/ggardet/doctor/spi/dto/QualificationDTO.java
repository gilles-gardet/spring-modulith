package com.ggardet.doctor.spi.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.UUID;

public record QualificationDTO(
        UUID id,

        @NotBlank String title,

        String institution,

        LocalDate graduationDate
) {
}

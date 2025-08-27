package com.ggardet.doctor.spi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record DoctorDTO(
        UUID id,

        @NotBlank String firstname,

        @NotBlank String lastname,

        @NotBlank String specialty,

        String licenseNumber,

        String phoneNumber,

        String email,

        UUID hospitalId,

        @Positive Integer yearsExperience
) {
}

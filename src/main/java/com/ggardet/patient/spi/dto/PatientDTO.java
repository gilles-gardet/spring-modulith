package com.ggardet.patient.spi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record PatientDTO(
        UUID id,
        @NotBlank String firstname,
        @NotBlank String lastname,
        @NotNull LocalDate birthDate,
        String socialSecurityNumber,
        String phoneNumber,
        @Email String email,
        UUID hospitalId,
        UUID serviceId
) {}
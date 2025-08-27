package com.ggardet.patient.spi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record MedicationDTO(
        UUID id,
        @NotNull UUID patientId,
        @NotBlank String name,
        String dosage,
        String frequency,
        LocalDate startDate,
        LocalDate endDate,
        UUID prescribingDoctorId,
        String instructions
) {}
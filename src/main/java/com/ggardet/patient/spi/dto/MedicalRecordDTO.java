package com.ggardet.patient.spi.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record MedicalRecordDTO(
        UUID id,
        @NotNull UUID patientId,
        @NotNull UUID doctorId,
        @NotNull LocalDateTime recordDate,
        String diagnosis,
        String treatment,
        String notes,
        Boolean followUpRequired
) {}
package com.ggardet.student.spi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.util.UUID;

@Builder(toBuilder = true)
public record StudentDTO(
        UUID id,

        @NotBlank
        @Size(max = 255)
        String firstname,

        @NotBlank
        @Size(max = 255)
        String lastname,

        @NotBlank
        String birthdate,

        UUID classroomId,

        UUID establishmentId) {
}

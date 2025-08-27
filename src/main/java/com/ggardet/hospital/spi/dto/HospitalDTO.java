package com.ggardet.hospital.spi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record HospitalDTO(
        UUID id,

        @NotBlank String name,

        @NotBlank String address,

        @NotNull @Positive Integer nbMaxServices,

        String phoneNumber,

        String director
) {
}

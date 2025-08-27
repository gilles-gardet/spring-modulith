package com.ggardet.establishment.spi.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;

import java.util.UUID;

@Builder(toBuilder = true)
public record EstablishmentDTO(
        UUID id,

        @NotBlank
        @Size(max = 255)
        String name,

        @Size(max = 255)
        String address,

        @Size(max = 15)
        String phoneNumber,

        @Email
        @Size(max = 255)
        String email,

        @NotNull
        @Min(value = 1)
        @Max(value = 99)
        Integer nbMaxClassroom
) {
}

package com.ggardet.establishment.spi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.util.Date;
import java.util.UUID;

@Builder(toBuilder = true)
public record ActivityDTO(
        UUID id,

        @NotBlank
        @Size(max = 255)
        String title,

        @NotBlank
        @Size(max = 255)
        String description,

        Date date,

        @NotBlank
        UUID establishmentId
) {
}

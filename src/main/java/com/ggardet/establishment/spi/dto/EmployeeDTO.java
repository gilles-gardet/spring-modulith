package com.ggardet.establishment.spi.dto;

import com.ggardet.core.enumeration.ValueOfEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.util.UUID;

@Builder(toBuilder = true)
public record EmployeeDTO(
        UUID id,

        @NotBlank
        @Size(max = 255)
        String firstname,

        @NotBlank
        @Size(max = 255)
        String lastname,

        @NotBlank
        @ValueOfEnum(field = "role", enumClass = RoleDTO.class, regexp = "TEACHER|ADMINISTRATION")
        String role,

        @Email
        String email,

        @NotBlank
        UUID establishmentId
) {
}

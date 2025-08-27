package com.ggardet.doctor.spi.exception;

import com.ggardet.core.exception.InternalNotFoundException;

import java.util.UUID;

public class SpecialtyNotFoundException extends InternalNotFoundException {
    public SpecialtyNotFoundException(final UUID specialtyId) {
        super(String.format("Specialty not found: %s", specialtyId));
    }
    public SpecialtyNotFoundException(final String specialtyName) {
        super(String.format("Specialty not found: %s", specialtyName));
    }
}
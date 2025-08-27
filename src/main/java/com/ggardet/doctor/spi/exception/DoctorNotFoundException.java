package com.ggardet.doctor.spi.exception;

import com.ggardet.core.exception.InternalNotFoundException;

import java.util.UUID;

public class DoctorNotFoundException extends InternalNotFoundException {
    public DoctorNotFoundException(final UUID doctorId) {
        super(String.format("Doctor not found: %s", doctorId));
    }
}
package com.ggardet.patient.spi.exception;

import java.util.UUID;

public class PatientNotFoundException extends RuntimeException {
    public PatientNotFoundException(final UUID id) {
        super(String.format("Patient with id %s not found", id));
    }
}
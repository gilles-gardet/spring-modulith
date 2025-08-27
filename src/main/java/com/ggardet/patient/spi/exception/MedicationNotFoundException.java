package com.ggardet.patient.spi.exception;

import java.util.UUID;

public class MedicationNotFoundException extends RuntimeException {
    public MedicationNotFoundException(final UUID id) {
        super(String.format("Medication with id %s not found", id));
    }
}
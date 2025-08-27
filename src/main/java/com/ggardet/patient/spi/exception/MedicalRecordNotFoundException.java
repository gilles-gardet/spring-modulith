package com.ggardet.patient.spi.exception;

import java.util.UUID;

public class MedicalRecordNotFoundException extends RuntimeException {
    public MedicalRecordNotFoundException(final UUID id) {
        super(String.format("Medical record with id %s not found", id));
    }
}
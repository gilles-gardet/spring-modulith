package com.ggardet.hospital.spi.exception;

import com.ggardet.core.exception.InternalNotFoundException;

import java.util.UUID;

public class TreatmentNotFoundException extends InternalNotFoundException {
    public TreatmentNotFoundException(final UUID hospitalId, final UUID treatmentId) {
        super(String.format("Treatment %s of hospital %s not found", treatmentId, hospitalId));
    }
}
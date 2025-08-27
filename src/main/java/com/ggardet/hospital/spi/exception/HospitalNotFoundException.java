package com.ggardet.hospital.spi.exception;

import com.ggardet.core.exception.InternalNotFoundException;

import java.util.UUID;

public class HospitalNotFoundException extends InternalNotFoundException {
    public HospitalNotFoundException(final UUID hospitalId) {
        super(String.format("Hospital not found: %s", hospitalId));
    }
}
package com.ggardet.hospital.spi.exception;

import com.ggardet.core.exception.InternalNotFoundException;

import java.util.UUID;

public class HospitalEmptyException extends InternalNotFoundException {
    public HospitalEmptyException(final UUID hospitalId, final String type) {
        super(String.format("Hospital %s does not have %s", hospitalId, type));
    }
}
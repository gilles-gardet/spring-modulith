package com.ggardet.establishment.spi.exception;

import com.ggardet.core.exception.InternalNotFoundException;

import java.util.UUID;

public class EstablishmentNotFoundException extends InternalNotFoundException {
    public EstablishmentNotFoundException(final UUID establishmentId) {
        super(String.format("Establishment not found: %s", establishmentId));
    }
}

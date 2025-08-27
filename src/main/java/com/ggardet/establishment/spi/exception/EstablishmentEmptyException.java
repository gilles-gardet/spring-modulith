package com.ggardet.establishment.spi.exception;

import com.ggardet.core.exception.InternalNotFoundException;

import java.util.UUID;

public class EstablishmentEmptyException extends InternalNotFoundException {
    public EstablishmentEmptyException(final UUID establishmentId, final String type) {
        super(String.format("Establishment %s does not have %s", establishmentId, type));
    }
}

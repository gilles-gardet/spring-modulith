package com.ggardet.classroom.spi.exception;

import com.ggardet.core.exception.InternalNotFoundException;

import java.util.UUID;

public class ClassroomNotFoundException extends InternalNotFoundException {
    public ClassroomNotFoundException(final UUID id, final String type) {
        super(String.format("%s not found: %s", type, id));
    }
}

package com.ggardet.classroom.spi.exception;

import com.ggardet.core.exception.InternalNotFoundException;

import java.util.UUID;

public class ClassroomFullException extends InternalNotFoundException {
    public ClassroomFullException(final int maxNumber, final UUID establishmentId) {
        super(String.format("Max number of classrooms of establishment '%s' has been reached ( '%s')", establishmentId, maxNumber));
    }
}

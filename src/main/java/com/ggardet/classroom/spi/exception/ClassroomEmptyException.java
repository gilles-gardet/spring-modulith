package com.ggardet.classroom.spi.exception;

import com.ggardet.core.exception.InternalNotFoundException;

import java.util.UUID;

public class ClassroomEmptyException extends InternalNotFoundException {
    public ClassroomEmptyException(final UUID classroomId, final String type) {
        super(String.format("Classroom '%s' does not have '%s'", classroomId, type));
    }
}

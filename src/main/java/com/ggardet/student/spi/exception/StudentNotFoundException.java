package com.ggardet.student.spi.exception;

import com.ggardet.core.exception.InternalNotFoundException;

import java.util.UUID;

public class StudentNotFoundException extends InternalNotFoundException {
    public StudentNotFoundException(final UUID id) {
        super(String.format("Student not found: %s", id));
    }
}

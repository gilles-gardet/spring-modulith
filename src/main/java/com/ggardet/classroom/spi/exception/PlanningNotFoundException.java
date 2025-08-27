package com.ggardet.classroom.spi.exception;

import com.ggardet.core.exception.InternalNotFoundException;

import java.util.UUID;

public class PlanningNotFoundException extends InternalNotFoundException {
    public PlanningNotFoundException(final UUID classroomId, final UUID planningId) {
        super(String.format("Planning %s of classroom %s not found", planningId, classroomId));
    }
}

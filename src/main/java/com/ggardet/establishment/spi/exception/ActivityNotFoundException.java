package com.ggardet.establishment.spi.exception;

import com.ggardet.core.exception.InternalNotFoundException;

import java.util.UUID;

public class ActivityNotFoundException extends InternalNotFoundException {
    public ActivityNotFoundException(final UUID establishmentId, final UUID activityId) {
        super(String.format("Activity %s of establishment %s not found", activityId, establishmentId));
    }
}

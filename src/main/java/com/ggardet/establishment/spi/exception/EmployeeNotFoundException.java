package com.ggardet.establishment.spi.exception;

import com.ggardet.core.exception.InternalNotFoundException;

import java.util.UUID;

public class EmployeeNotFoundException extends InternalNotFoundException {
    public EmployeeNotFoundException(final UUID establishmentId, final UUID employeeId) {
        super(String.format("Employee %s of establishment %s not found", employeeId, establishmentId));
    }
}

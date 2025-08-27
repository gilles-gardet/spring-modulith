package com.ggardet.core.exception;

public record ErrorResponse(String message, Object[] messageArgs) {
}

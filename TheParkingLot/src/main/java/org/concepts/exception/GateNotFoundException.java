package org.concepts.exception;

public class GateNotFoundException extends RuntimeException {
    public GateNotFoundException(String message) {
        super(message);
    }
}

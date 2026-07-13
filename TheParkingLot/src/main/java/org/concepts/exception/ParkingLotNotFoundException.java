package org.concepts.exception;

public class ParkingLotNotFoundException extends RuntimeException {
    public ParkingLotNotFoundException(String message) {
        super(message);
    }
}

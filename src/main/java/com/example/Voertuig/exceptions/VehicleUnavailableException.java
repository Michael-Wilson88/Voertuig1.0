package com.example.Voertuig.exceptions;

public class VehicleUnavailableException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public VehicleUnavailableException(Long id) {
        super("Vehicle is unavailable");
    }

}

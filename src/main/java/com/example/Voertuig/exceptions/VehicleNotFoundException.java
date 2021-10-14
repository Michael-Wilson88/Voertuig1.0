package com.example.Voertuig.exceptions;

public class VehicleNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public VehicleNotFoundException(Long id) {
        super("Vehicle not found.");
    }
}

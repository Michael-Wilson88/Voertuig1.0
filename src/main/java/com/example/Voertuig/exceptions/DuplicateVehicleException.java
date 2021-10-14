package com.example.Voertuig.exceptions;

import com.example.Voertuig.domain.Vehicle;

public class DuplicateVehicleException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DuplicateVehicleException(Vehicle vehicle) {
        super("Vehicle is already in this booking list.");
    }
}

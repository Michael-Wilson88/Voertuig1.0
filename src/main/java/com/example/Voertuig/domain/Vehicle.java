package com.example.Voertuig.domain;

import lombok.Data;

@Data
public class Vehicle {

    private String name;
    private boolean isAvailable;
    private static int counter = 0;

    public Vehicle(String name, boolean isAvailable) {
        this.name = name;
        this.isAvailable = isAvailable;
        counter++;
    }

    public Vehicle() {
        counter++;
    }

    public static int totalNoVehicle() {
        return counter;
    }


}

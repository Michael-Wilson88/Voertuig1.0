package com.example.Voertuig.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String brandName;
    private String brandModel;
    private String vehicleType;
    private long bookingDays;
    private LocalDate returnDate;
    private boolean isAvailable;
    private static int counter = 0;

    @OneToMany(mappedBy = "vehicle")
    private List<Booking> bookings;

    public Vehicle(long id, String brandName, String brandModel, String vehicleType, boolean isAvailable) {
        this.id = id;
        this.brandName = brandName;
        this.brandModel = brandModel;
        this.vehicleType = vehicleType;
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

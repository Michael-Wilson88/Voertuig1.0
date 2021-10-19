package com.example.Voertuig.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Transient
    private List<Period> unavailablePeriods;

    @OneToMany(mappedBy = "vehicle")
    private List<Booking> bookings;


    public Vehicle() {
        counter++;
    }

    public static int totalNoVehicle() {
        return counter;
    }


}

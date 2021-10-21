package com.example.Voertuig.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Entity
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vehicleId;
    private String brandName;
    private String brandModel;
    private String vehicleType;
    @JsonIgnore
    private long bookingDays;
    @JsonIgnore
    private LocalDate returnDate;

    private static int counter = 0;

    @JsonIgnore
    @Transient
    private List<Period> bookedPeriods = new ArrayList<>();

    @JsonIgnore
    @ElementCollection
    public List<LocalDate> bookedDates = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "vehicle")
    private List<Booking> bookings;

    public Vehicle(Long vehicleId, String brandName, String brandModel, String vehicleType, long bookingDays, LocalDate returnDate, List<Period> bookedPeriods, List<LocalDate> bookedDates, List<Booking> bookings) {
        this.vehicleId = vehicleId;
        this.brandName = brandName;
        this.brandModel = brandModel;
        this.vehicleType = vehicleType;
        this.bookingDays = bookingDays;
        this.returnDate = returnDate;
        this.bookedPeriods = bookedPeriods;
        this.bookedDates = bookedDates;
        this.bookings = bookings;
    }

    public Vehicle() {
        counter++;
    }

    public static int totalNoVehicle() {
        return counter;
    }


}

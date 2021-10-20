package com.example.Voertuig.payload.response;

import com.example.Voertuig.domain.User;
import com.example.Voertuig.domain.Vehicle;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;

public class BookingResponse {

    private Long id;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "password", "role", "roles", "email", "bookings", "address", "zipcode", "country"})
    private User user;

    @JsonIgnoreProperties({"vehicleType", "isAvailable", "bookings", "bookingDays", "returnDate", "available"})
    private Vehicle vehicle;
    private LocalDate startDate;
    private LocalDate returnDate;
    private Long days;

    public BookingResponse(Long id, User user, Vehicle vehicle, LocalDate startDate, LocalDate returnDate, Long days) {
        this.id = id;
        this.user = user;
        this.vehicle = vehicle;
        this.startDate = startDate;
        this.returnDate = returnDate;
        this.days = days;
    }
}

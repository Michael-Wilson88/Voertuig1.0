package com.example.Voertuig.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "Booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "customer_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "password", "role", "roles", "email", "bookings", "address", "zipcode", "country"})
    private User user;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "vehicle_id")
    @JsonIgnoreProperties({"vehicleType", "isAvailable", "bookings", "bookingDays", "returnDate", "available"})
    private Vehicle vehicle;

    private LocalDate startDate;
    private LocalDate returnDate;
    private Long days;

    public Booking(Long id, User user, Vehicle vehicle, LocalDate startDate, LocalDate returnDate, Long days) {
        this.id = id;
        this.user = user;
        this.vehicle = vehicle;
        this.startDate = startDate;
        this.returnDate = returnDate;
        this.days = days;

    }

    public Booking() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public Long getDays() {
        return days;
    }

    public void setDays(Long days) {
        this.days = days;
    }
}



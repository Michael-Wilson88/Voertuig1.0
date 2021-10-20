package com.example.Voertuig.payload.response;

import com.example.Voertuig.domain.Booking;
import lombok.Data;

import java.util.List;

@Data
public class CustomerResponse {

    private Long customerId;
    private String username;
    private String email;
    private String address;
    private String zipcode;
    private String country;
    private List<Booking> bookings;

    public CustomerResponse(Long customerId, String username, String email, String address, String zipcode, String country, List<Booking> bookings) {
        this.customerId = customerId;
        this.username = username;
        this.email = email;
        this.address = address;
        this.zipcode = zipcode;
        this.country = country;
        this.bookings = bookings;
    }
}

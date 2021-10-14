package com.example.Voertuig.domain;

import lombok.Data;

import javax.persistence.OneToOne;

@Data
public class Customer extends User {

    private String address;
    private String zipcode;
    private String country;

    @OneToOne
    private Booking booking;

    public Customer(String username, String email, String password, String address, String zipcode, String country) {
        super(username, email, password);
        this.address = address;
        this.zipcode = zipcode;
        this.country = country;
    }
}

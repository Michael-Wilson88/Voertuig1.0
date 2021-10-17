package com.example.Voertuig.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Customer extends User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private String address;
    private String zipcode;
    private String country;

    @OneToMany
    private List<Booking> bookings;

    public Customer(String username, String email, String password, String address, String zipcode, String country) {
        super(username, email, password);
        this.address = address;
        this.zipcode = zipcode;
        this.country = country;
    }

    public Customer() {

    }
}

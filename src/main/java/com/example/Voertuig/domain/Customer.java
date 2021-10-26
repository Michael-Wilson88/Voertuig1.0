package com.example.Voertuig.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Customer extends User {

    @JsonIgnore
    private String address;
    @JsonIgnore
    private String zipcode;
    @JsonIgnore
    private String country;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
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

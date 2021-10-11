package com.example.Voertuig.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Data
@Entity
public class Booking {

    @Id
    private Long id;

    @OneToOne
    private User user;
    @OneToMany
    private List<Vehicle> vehicles;


}

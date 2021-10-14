package com.example.Voertuig.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Data
@Entity
@Component
public class Booking {

    @Id
    private Long id;

    @OneToOne
    private User user;
    @OneToMany
    private List<Vehicle> vehicles;


}

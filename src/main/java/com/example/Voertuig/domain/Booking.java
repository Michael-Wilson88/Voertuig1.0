package com.example.Voertuig.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Component
public class Booking {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private User user;
    @OneToOne
    private Vehicle vehicle;


}

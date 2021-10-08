package com.example.Voertuig.repository;

import com.example.Voertuig.domain.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    Optional<Vehicle> findByAvailable(boolean isAvailable);

}

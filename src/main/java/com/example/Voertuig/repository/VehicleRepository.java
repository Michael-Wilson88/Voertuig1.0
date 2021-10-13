package com.example.Voertuig.repository;

import com.example.Voertuig.domain.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

//    Optional<Vehicle> findByAvailable(boolean isAvailable);

}

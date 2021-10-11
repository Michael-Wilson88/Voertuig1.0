package com.example.Voertuig.serviceImpl;

import com.example.Voertuig.domain.Vehicle;
import com.example.Voertuig.payload.request.VehicleRequest;
import com.example.Voertuig.repository.VehicleRepository;
import com.example.Voertuig.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class VehicleServiceImpl implements VehicleService {

    private VehicleRepository vehicleRepository;

    @Autowired
    public void setVehicleRepository (VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public ResponseEntity<?> createVehicle(VehicleRequest vehicleRequest) {

        Vehicle vehicle = new Vehicle();
        vehicle.setBrandName(vehicleRequest.getBrandName());
        vehicle.setBrandModel(vehicleRequest.getBrandModel());
        vehicle.setVehicleType(vehicleRequest.getVehicleType());
        vehicle.setAvailable(true);

        vehicleRepository.save(vehicle);

        return new ResponseEntity<>("Vehicle " + vehicle.getBrandName() + " has been created.", HttpStatus.OK);
    }

    public ResponseEntity<?> getVehicles() {
        if (vehicleRepository.findAll().isEmpty()) {
            return ResponseEntity.status(400).body("No Vehicles found.");
        }
        return ResponseEntity.ok(vehicleRepository.findAll());
    }


}

package com.example.Voertuig.service;

import com.example.Voertuig.payload.request.VehicleRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface VehicleService {

    ResponseEntity<?> createVehicle(VehicleRequest vehicleRequest);
    ResponseEntity<?> getVehicles();
    void updateVehicle(VehicleRequest vehicleRequest);
    ResponseEntity<Object> getVehicle(long id);
}

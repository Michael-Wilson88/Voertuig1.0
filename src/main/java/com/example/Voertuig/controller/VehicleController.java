package com.example.Voertuig.controller;

import com.example.Voertuig.payload.request.VehicleRequest;
import com.example.Voertuig.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class VehicleController extends BaseController {

    private VehicleService vehicleService;

    @Autowired
    public void setVehicleService(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping(value = "/vehicles")
    public ResponseEntity<?> getVehicles(){
        return vehicleService.getVehicles();
    }
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    @PostMapping(value = "/createvehicle")
    public ResponseEntity<?> createVehicle(@Valid @RequestBody VehicleRequest vehicleRequest) {
        return vehicleService.createVehicle(vehicleRequest);
    }

//    @DeleteMapping

}

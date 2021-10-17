package com.example.Voertuig.controller;

import com.example.Voertuig.payload.request.VehicleRequest;
import com.example.Voertuig.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class VehicleController {

    private VehicleService vehicleService;

    @Autowired
    public void setVehicleService(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping(value = "/vehicles")
    public ResponseEntity<?> getVehicles(){
        return vehicleService.getVehicles();
    }
    // @PreAuthorize als security
    @PostMapping(value = "/createvehicle")
    public ResponseEntity<?> createVehicle(@Valid @RequestBody VehicleRequest vehicleRequest) {
        return vehicleService.createVehicle(vehicleRequest);
    }

}

package com.example.Voertuig.controller;

import com.example.Voertuig.payload.request.VehicleRequest;
import com.example.Voertuig.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.logging.Logger;

@RestController
public class VehicleController extends BaseController {

    private VehicleService vehicleService;

    Logger logger = Logger.getLogger(VehicleController.class.getName());

    @Autowired
    public void setVehicleService(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping(value = "/vehicles")
    public ResponseEntity<?> getVehicles(){
        logger.info("Getting vehicle list.");
        return vehicleService.getVehicles();
    }

    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    @PostMapping(value = "/createvehicle")
    public ResponseEntity<?> createVehicle(@Valid @RequestBody VehicleRequest vehicleRequest) {
        logger.info("Admin created new vehicle.");
        return vehicleService.createVehicle(vehicleRequest);
    }

//    @DeleteMapping

}

package com.example.Voertuig.controller;

import com.example.Voertuig.domain.Vehicle;
import com.example.Voertuig.payload.request.VehicleRequest;
import com.example.Voertuig.service.VehicleService;
import org.bouncycastle.asn1.esf.SPuri;
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

    @GetMapping(value = "/vehicles/{id}")
    public ResponseEntity<Object> getVehicle(@PathVariable ("id") long id) {
        logger.info("Retrieving vehicle: " + id + " data.");
        return vehicleService.getVehicle(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/createvehicle")
    public ResponseEntity<?> createVehicle(@Valid @RequestBody VehicleRequest vehicleRequest) {
        logger.info("Admin created new vehicle.");
        return vehicleService.createVehicle(vehicleRequest);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/updatevehicle")
    public ResponseEntity<?> updateAvailability(@Valid @RequestBody VehicleRequest vehicleRequest) {
        logger.info("Vehicle: " + vehicleRequest.getId() + " has been updated." );
        vehicleService.updateVehicle(vehicleRequest);
        return ResponseEntity.ok("Vehicle: " + vehicleRequest.getId() + " has been updated." );
    }

    @GetMapping(value = "/vehicles/total")// Op de een of andere manier begint hij altijd vanaf 1 terwijl ik counter = 0 aangeef in Vehicle domain
    public ResponseEntity<?> getTotalNoVehicles() {
        return ResponseEntity.ok().body("Total nr of vehicles is: " + Vehicle.getCounter());
    }


}

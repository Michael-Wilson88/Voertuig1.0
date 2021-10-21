package com.example.Voertuig.controller;

import com.example.Voertuig.exceptions.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolationException;
import java.time.DateTimeException;

@RestController
@CrossOrigin
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = DuplicateVehicleException.class)
    public ResponseEntity<Object> exception(DuplicateVehicleException duplicateVehicleException) {
        return ResponseEntity.status(404).body(duplicateVehicleException.getMessage());
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<Object> exception(UserNotFoundException userNotFoundException) {
        return ResponseEntity.status(404).body(userNotFoundException.getMessage());
    }

    @ExceptionHandler(value = VehicleNotFoundException.class)
    public ResponseEntity<Object> exception(VehicleNotFoundException vehicleNotFoundException) {
        return ResponseEntity.status(404).body(vehicleNotFoundException.getMessage());
    }

    @ExceptionHandler(value = VehicleUnavailableException.class)
    public ResponseEntity<Object> exception(VehicleUnavailableException vehicleUnavailableException) {
        return ResponseEntity.status(404).body(vehicleUnavailableException.getMessage());
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<Object> exception(ConstraintViolationException constraintViolationException) {
        return ResponseEntity.status(400).body(constraintViolationException.getMessage());
    }

    @ExceptionHandler(value = DateTimeException.class)
    public ResponseEntity<Object> exception(DateTimeException dateTimeException) {
        return ResponseEntity.status(400).body(dateTimeException.getMessage());
    }

    @ExceptionHandler(value = RecordNotFoundException.class)
    public ResponseEntity<Object> exception(RecordNotFoundException recordNotFoundException) {
        return ResponseEntity.status(404).body(recordNotFoundException.getMessage());
    }
}
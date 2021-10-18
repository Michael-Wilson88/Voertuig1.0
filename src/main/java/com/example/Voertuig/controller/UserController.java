package com.example.Voertuig.controller;

// De reservering die een User gemaakt heeft moet zichtbaar zijn. Dan wordt het zoiets: app/user/bookings
// Alle reserveringen die er zijn moeten alleen voor de ADMIN zichtbaar zijn. app/bookings
// Ik moet dit dus waarschijnlijk vanaf de controller definieren en niet vanuit de servicelayer, in de service layer moet de methode denk ik geen responseEntity zijn

import com.example.Voertuig.domain.Booking;
import com.example.Voertuig.payload.request.BookVehicleRequest;
import com.example.Voertuig.payload.request.SignupRequest;
import com.example.Voertuig.service.BookingService;
import com.example.Voertuig.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

// Check of ik 2 services kan aanroepen in deze controller class
@RestController
public class UserController {

    private BookingService bookingService;

    private CustomerService customerService;

    @Autowired
    public void setBookingService(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(value = "/users")
    public ResponseEntity<Object> getCustomers() {
        return ResponseEntity.ok().body(customerService.getCustomers());
    }


    @PostMapping(value = "/{username}/booking")
    public ResponseEntity<Object> addBooking(@PathVariable("username") String userName, @Valid @RequestBody BookVehicleRequest bookVehicleRequest) {
        return ResponseEntity.ok().body(bookingService.bookVehicle(userName, bookVehicleRequest));
    }

    @PostMapping(value = "/createtempuser")
    public ResponseEntity<?> createTempUser(@Valid @RequestBody SignupRequest signupRequest) {
        return customerService.createTempUser(signupRequest);
    }
}

//Endpoints voor user zijn:
///user/bookings
///user/vehicles ?
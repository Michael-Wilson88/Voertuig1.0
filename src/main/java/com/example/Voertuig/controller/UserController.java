package com.example.Voertuig.controller;



// Ik moet dit dus waarschijnlijk vanaf de controller definieren en niet vanuit de servicelayer, in de service layer moet de methode denk ik geen responseEntity zijn

import com.example.Voertuig.domain.Booking;
import com.example.Voertuig.payload.request.BookVehicleRequest;
import com.example.Voertuig.payload.request.SignupRequest;
import com.example.Voertuig.service.BookingService;
import com.example.Voertuig.service.CustomerService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.logging.Logger;


@RestController
public class UserController extends BaseController {

    private BookingService bookingService;

    private CustomerService customerService;

    Logger logger = Logger.getLogger(UserController.class.getName());

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
        logger.info("Getting list of Users.");
        return ResponseEntity.ok().body(customerService.getCustomers());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/users/{username}")
    public ResponseEntity<Object> getCustomer(@PathVariable("username") String userName) {
        logger.info("Getting User info.");
        return customerService.getUser(userName);
    }

    @PostMapping(value = "/users/{username}/booking")
    public ResponseEntity<Object> addBooking(@PathVariable("username") String userName, @Valid @RequestBody BookVehicleRequest bookVehicleRequest) {
        logger.info(userName + " booked vehicle Nr: " + bookVehicleRequest.getVehicleId());
        return bookingService.bookVehicle(userName, bookVehicleRequest);
    }

}

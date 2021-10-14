package com.example.Voertuig.controller;

import com.example.Voertuig.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingController {

    private BookingService bookingService;

    @Autowired
    public void setBookingService(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping(value = "/bookings")
    public ResponseEntity<Object> getBookings() {
        return ResponseEntity.ok(bookingService.getBookings());
    }

//    @GetMapping(value = "/user/bookings")

}

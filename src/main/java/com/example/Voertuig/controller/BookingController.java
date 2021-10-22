package com.example.Voertuig.controller;

import com.example.Voertuig.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.logging.Logger;

@RestController
public class BookingController extends BaseController {

    private BookingService bookingService;

    Logger logger = Logger.getLogger(BookingController.class.getName());

    @Autowired
    public void setBookingService(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping(value = "/bookings")
    public ResponseEntity<Object> getBookings() {
        logger.info("Retrieving bookings list.");
        return ResponseEntity.ok(bookingService.getBookings());
    }

    @DeleteMapping(value = "/{username}/{id}")
    public ResponseEntity<Object> deleteBooking(@PathVariable("username")String username, @PathVariable("id") Long id) {
        logger.info(username + " deleted booking Nr: " + id);
        return bookingService.deleteBooking(username, id);
    }


}

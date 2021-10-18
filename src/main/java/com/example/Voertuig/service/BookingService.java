package com.example.Voertuig.service;

import com.example.Voertuig.domain.Booking;
import com.example.Voertuig.payload.request.BookVehicleRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.Collection;

@Service
public interface BookingService {

    Collection<Booking> getBookings();
//    ResponseEntity<Object> bookVehicle(String userName, BookVehicleRequest bookVehicleRequest);
   Booking bookVehicle(String userName, BookVehicleRequest bookVehicleRequest);
}

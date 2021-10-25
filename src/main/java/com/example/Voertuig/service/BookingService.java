package com.example.Voertuig.service;

import com.example.Voertuig.domain.Booking;
import com.example.Voertuig.domain.Customer;
import com.example.Voertuig.payload.request.BookVehicleRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;

@Service
public interface BookingService {

    Collection<Booking> getBookings();
    ResponseEntity<Object> bookVehicle(String userName, BookVehicleRequest bookVehicleRequest);
    ResponseEntity<Object> deleteBooking(String userName, Long id);
    Customer checkIfCustomerExists(String userName);
    LocalDate startDateFormatter(BookVehicleRequest bookVehicleRequest);


}

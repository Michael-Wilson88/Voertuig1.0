package com.example.Voertuig.serviceImpl;

import com.example.Voertuig.domain.Booking;
import com.example.Voertuig.domain.Vehicle;
import com.example.Voertuig.repository.BookingRepository;
import com.example.Voertuig.repository.UserRepository;
import com.example.Voertuig.repository.VehicleRepository;
import com.example.Voertuig.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;

public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public void setBookingRepository(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public void createBooking(Vehicle vehicle) {

        Booking booking = new Booking();

    }

}

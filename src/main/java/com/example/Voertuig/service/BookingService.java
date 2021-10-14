package com.example.Voertuig.service;

import com.example.Voertuig.domain.Booking;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface BookingService {

    Collection<Booking> getBookings();
}

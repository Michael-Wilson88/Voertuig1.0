package com.example.Voertuig.service;

import com.example.Voertuig.domain.Booking;
import com.example.Voertuig.payload.request.SignupRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {
    Object getCustomers();
    ResponseEntity<Object> getUser(String userName);
}

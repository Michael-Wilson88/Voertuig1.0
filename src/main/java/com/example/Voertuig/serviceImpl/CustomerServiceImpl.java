package com.example.Voertuig.serviceImpl;

import com.example.Voertuig.repository.CustomerRepository;
import com.example.Voertuig.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public ResponseEntity<?> getCustomers(){
        return ResponseEntity.ok(customerRepository.findAll());
    }


}

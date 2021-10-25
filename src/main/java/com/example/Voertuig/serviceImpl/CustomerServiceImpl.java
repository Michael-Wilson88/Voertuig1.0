package com.example.Voertuig.serviceImpl;

import com.example.Voertuig.domain.Customer;
import com.example.Voertuig.exceptions.UserNotFoundException;
import com.example.Voertuig.payload.request.SignupRequest;
import com.example.Voertuig.payload.response.ResponseBuilder;
import com.example.Voertuig.repository.CustomerRepository;
import com.example.Voertuig.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    public ResponseEntity<Object> getUser(String userName) {
        Customer customer = customerRepository.findCustomerByUsername(userName)
                .orElseThrow(() -> new UserNotFoundException(userName));
        return ResponseEntity.ok().body(ResponseBuilder.customerResponse(customer));
    }

}

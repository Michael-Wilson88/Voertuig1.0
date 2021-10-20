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

    // TODO: 18-10-2021 Deze temp method weghalen en vervangen door security en authorizatie
    public ResponseEntity<?> createTempUser(SignupRequest signupRequest) {

        Customer customer = new Customer();
        customer.setUsername(signupRequest.getUsername());
        customer.setEmail(signupRequest.getEmail());
        customer.setPassword(signupRequest.getPassword());
        customer.setAddress(signupRequest.getAddress());
        customer.setZipcode(signupRequest.getZipcode());
        customer.setCountry(signupRequest.getCountry());

        customerRepository.save(customer);
        return new ResponseEntity<>("User " + customer.getUsername() + " has been created.", HttpStatus.OK);

    }

    public ResponseEntity<Object> getUser(String userName) {
        Customer customer = customerRepository.findCustomerByUsername(userName)
                .orElseThrow(() -> new UserNotFoundException(userName));
        return ResponseEntity.ok().body(ResponseBuilder.customerResponse(customer));
    }

//    public ResponseEntity<?> getBookings() {
//
//    }

}

package com.example.demo.serviceImpl;

import com.example.Voertuig.domain.Customer;
import com.example.Voertuig.payload.request.SignupRequest;
import com.example.Voertuig.payload.response.MessageResponse;
import com.example.Voertuig.repository.CustomerRepository;
import com.example.Voertuig.repository.UserRepository;
import com.example.Voertuig.service.CustomerService;
import com.example.Voertuig.serviceImpl.AuthorizationService;
import com.example.Voertuig.serviceImpl.CustomerServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @InjectMocks
    private final CustomerService customerService = new CustomerServiceImpl();

    @InjectMocks
    private AuthorizationService authorizationService;

    @Mock
    private SignupRequest signupRequest;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Test
    void userNameShouldReturnCustomerInfo() {

        Customer customer = new Customer();
        Mockito.when(customerRepository.findCustomerByUsername(customer.getUsername())).thenReturn(Optional.of(customer));

        ResponseEntity<?> responseEntity =  customerService.getUser(customer.getUsername());

        Assertions.assertEquals(200,  responseEntity.getStatusCodeValue());
        Assertions.assertEquals((ResponseEntity.ok(customer)), ResponseEntity.ok(customer));
    }

    @Test
    void existingUserNameShouldReturnError() {
        Mockito.when(userRepository.existsByUsername(signupRequest.getUsername())).thenReturn(true);

        ResponseEntity<MessageResponse> responseEntity = authorizationService.registerUser(signupRequest);

        Assertions.assertEquals(400, responseEntity.getStatusCodeValue());
        Assertions.assertEquals(ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!")),
                                ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!")));
    }

    @Test
    void existingEmailShouldReturnError() {
        Mockito.when(userRepository.existsByEmail(signupRequest.getEmail())).thenReturn(true);

        ResponseEntity<MessageResponse> responseEntity = authorizationService.registerUser(signupRequest);

        Assertions.assertEquals(400, responseEntity.getStatusCodeValue());
        Assertions.assertEquals(ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already taken!")),
                                ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already taken!")));
    }

}
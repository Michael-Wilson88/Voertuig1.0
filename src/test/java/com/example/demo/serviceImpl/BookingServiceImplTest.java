package com.example.demo.serviceImpl;

import com.example.Voertuig.domain.Booking;
import com.example.Voertuig.domain.Customer;
import com.example.Voertuig.exceptions.UserNotFoundException;
import com.example.Voertuig.payload.request.BookVehicleRequest;
import com.example.Voertuig.repository.BookingRepository;
import com.example.Voertuig.repository.CustomerRepository;
import com.example.Voertuig.repository.VehicleRepository;
import com.example.Voertuig.service.BookingService;
import com.example.Voertuig.serviceImpl.BookingServiceImpl;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.time.DateTimeException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

@AutoConfigureTestDatabase
@ExtendWith(MockitoExtension.class)
public class BookingServiceImplTest {

    @InjectMocks
    private final BookingService bookingService = new BookingServiceImpl();

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private Booking booking;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private VehicleRepository vehicleRepository;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    void nonExistingUsernameShouldReturnError() {

        Customer customer = mock(Customer.class);

        Throwable exception = assertThrows(UserNotFoundException.class, () -> bookingService.checkIfCustomerExists(customer.getUsername()));

        assertEquals("User " + customer.getUsername() + " does not exist.", exception.getMessage());
    }

    @Test
    void wrongStartDateEntryShouldReturnException() {

        BookVehicleRequest bookVehicleRequest = new BookVehicleRequest();
        bookVehicleRequest.setVehicleId(1L);
        bookVehicleRequest.setBeginDate("01-JULY-2021");

        Throwable exception = assertThrows(DateTimeException.class, () -> bookingService.startDateFormatter(bookVehicleRequest));
        assertEquals("Text '01-JULY-2021' could not be parsed at index 3", exception.getMessage());
    }
}

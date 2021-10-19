package com.example.Voertuig.serviceImpl;

import com.example.Voertuig.domain.Booking;
import com.example.Voertuig.domain.Customer;
import com.example.Voertuig.domain.Vehicle;
import com.example.Voertuig.exceptions.DuplicateVehicleException;
import com.example.Voertuig.exceptions.UserNotFoundException;
import com.example.Voertuig.exceptions.VehicleNotFoundException;
import com.example.Voertuig.exceptions.VehicleUnavailableException;
import com.example.Voertuig.payload.request.BookVehicleRequest;
import com.example.Voertuig.payload.request.VehicleRequest;
import com.example.Voertuig.repository.BookingRepository;
import com.example.Voertuig.repository.CustomerRepository;
import com.example.Voertuig.repository.UserRepository;
import com.example.Voertuig.repository.VehicleRepository;
import com.example.Voertuig.service.BookingService;
import com.example.Voertuig.service.CustomerService;
import com.example.Voertuig.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.*;

// TODO: 17-10-2021 Ik heb de relatie verandert van vehicle en bookings naar een OneToOne. Dit zodat elke booking maar 1 vehicle heeft en het makkelijker op te zoeken is
// dan heeft elke customer meerdere bookings


@Service
public class BookingServiceImpl implements BookingService {


    private VehicleService vehicleService;

    private CustomerService customerService;
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    public void setBookingRepository(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }


    public Collection<Booking> getBookings() {
        return bookingRepository.findAll();
    }

    public Customer checkIfCustomerExists(String userName) {

        Optional<Customer> optionalCustomer = customerRepository.findCustomerByUsername(userName);
        if (optionalCustomer.isEmpty()) {
            throw new UserNotFoundException(userName);
        }
        return optionalCustomer.get();
    }

    public Vehicle checkIfVehicleExists(long id) {

        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(id);

        if (optionalVehicle.isEmpty()) {
            throw new VehicleNotFoundException(id);
        }
        return optionalVehicle.get();
    }

    public LocalDate startDateFormatter(BookVehicleRequest bookVehicleRequest) {

        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .appendPattern("dd-MM-yyyy")
                .toFormatter(Locale.ENGLISH);

        return LocalDate.parse(bookVehicleRequest.getBeginDate(), formatter);
    }

    public LocalDate returnDateFormatter(BookVehicleRequest bookVehicleRequest) {

        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .appendPattern("dd-MM-yyyy")
                .toFormatter(Locale.ENGLISH);

        return LocalDate.parse(bookVehicleRequest.getEndDate(), formatter);
    }


    // TODO: 18-10-2021 createBooking() en dan addVehicleToBooking() apart

    public ResponseEntity<Object> bookVehicle(String userName, BookVehicleRequest bookVehicleRequest) {

        Vehicle vehicle = checkIfVehicleExists(bookVehicleRequest.getVehicleId());
        Customer customer = checkIfCustomerExists(userName);
        Booking booking = new Booking();

        List<Booking> customerBookings = customer.getBookings();
        List<Booking> vehicleBookings = vehicle.getBookings();

        LocalDate startDate =  startDateFormatter(bookVehicleRequest);
        LocalDate returnDate = returnDateFormatter(bookVehicleRequest);
        Period bookingPeriod = Period.between(startDate, returnDate);
        long bookingDays = bookingPeriod.getDays();

        List<Period> periods = vehicle.getUnavailablePeriods();

        if (!periods.contains(bookingPeriod)|| periods.isEmpty()) {
            periods.add(bookingPeriod);
            vehicleBookings.add(booking);
            customerBookings.add(booking);
            booking.setStartDate(startDate);
            booking.setReturnDate(returnDate);
            booking.setVehicle(vehicle);
            booking.setUser(customer);
            booking.setDays(bookingDays);
            customerBookings.add(booking);
        }
        else if (vehicle.getUnavailablePeriods().contains(bookingPeriod)) {
            throw new VehicleUnavailableException(bookVehicleRequest.getVehicleId());
        }



        bookingRepository.save(booking);

        return ResponseEntity.ok().body("Booking " + booking.getId() + " has been created");
    }

}

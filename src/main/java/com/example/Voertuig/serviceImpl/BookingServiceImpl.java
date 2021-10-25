package com.example.Voertuig.serviceImpl;

import com.example.Voertuig.domain.Booking;
import com.example.Voertuig.domain.Customer;
import com.example.Voertuig.domain.Vehicle;
import com.example.Voertuig.exceptions.*;
import com.example.Voertuig.payload.request.BookVehicleRequest;
import com.example.Voertuig.payload.request.DeleteBookingRequest;
import com.example.Voertuig.payload.request.VehicleRequest;
import com.example.Voertuig.repository.BookingRepository;
import com.example.Voertuig.repository.CustomerRepository;
import com.example.Voertuig.repository.UserRepository;
import com.example.Voertuig.repository.VehicleRepository;
import com.example.Voertuig.service.BookingService;
import com.example.Voertuig.service.CustomerService;
import com.example.Voertuig.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// TODO: 17-10-2021 Ik heb de relatie verandert van vehicle en bookings naar een OneToOne. Dit zodat elke booking maar 1 vehicle heeft en het makkelijker op te zoeken is
// dan heeft elke customer meerdere bookings


@Service
public class BookingServiceImpl implements BookingService {


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

    public ResponseEntity<Object> deleteBooking(String userName, Long id) {

        Booking booking = checkIfBookingExists(id);
        Customer customer = checkIfCustomerExists(userName);
        Vehicle vehicle = booking.getVehicle();
        List<LocalDate> daysInBetween = datesChecker(booking.getStartDate(), booking.getReturnDate());
        vehicle.getBookedDates().removeAll(daysInBetween);
        customer.getBookings().remove(booking);
        bookingRepository.deleteById(id);

        return new ResponseEntity<>("Booking " + id + " deleted.", HttpStatus.OK);
    }

    public Collection<Booking> getBookings() {
        return bookingRepository.findAll();
    }

    public Booking checkIfBookingExists(Long id) {
        Optional<Booking> optionalBooking = bookingRepository.findById(id);
        if (optionalBooking.isEmpty()) {
            throw new RecordNotFoundException(id);
        }
        return optionalBooking.get();
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
//    public LocalDate dateFormatter(String givenDate) {
//        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
//                .parseCaseInsensitive()
//                .appendPattern("dd-MM-yyyy")
//                .toFormatter(Locale.ENGLISH);
//        return LocalDate.parse(givenDate, formatter);
//    }

    public List<LocalDate> datesChecker(LocalDate startDate, LocalDate returnDate) {

        return startDate.datesUntil(returnDate.plusDays(1)).collect(Collectors.toList());
    }


    public ResponseEntity<Object> bookVehicle(String userName, BookVehicleRequest bookVehicleRequest) {

        Vehicle vehicle = checkIfVehicleExists(bookVehicleRequest.getVehicleId());
        Customer customer = checkIfCustomerExists(userName);
        Booking booking = new Booking();

        List<Booking> vehicleBookings = vehicle.getBookings();
        List<LocalDate> bookedDates = vehicle.getBookedDates();

        LocalDate startDate =  startDateFormatter(bookVehicleRequest);
        LocalDate returnDate = returnDateFormatter(bookVehicleRequest);
        List<LocalDate> daysInBetween = datesChecker(startDate, returnDate);
        Period bookingPeriod = Period.between(startDate, returnDate);
        long bookingDays = bookingPeriod.getDays();

        if (bookingDays <= 0) {
            throw new IncorrectDateException();
        }
        if(bookedDates.stream().anyMatch(daysInBetween::contains)) {
            throw new VehicleUnavailableException(vehicle.getVehicleId());
        }
        else if (bookedDates.stream().noneMatch(daysInBetween::contains)) {
            booking.setStartDate(startDate);
            booking.setReturnDate(returnDate);
            booking.setVehicle(vehicle);
            booking.setUser(customer);
            booking.setDays(bookingDays);
            bookedDates.addAll(daysInBetween);
            vehicle.setBookedDates(bookedDates);
            vehicleBookings.add(booking);
            vehicleRepository.save(vehicle);
            bookingRepository.save(booking);
            return ResponseEntity.ok().body("Booking " + booking.getBookingId() + " has been created");
        }
        return ResponseEntity.ok().body("BRRRT error, error");
    }
}

package com.example.Voertuig.payload.response;

import com.example.Voertuig.domain.Booking;
import com.example.Voertuig.domain.Customer;
import com.example.Voertuig.domain.Vehicle;

public class ResponseBuilder {

    public static VehicleResponse vehicleResponse(Vehicle vehicle) {
        return new VehicleResponse(
                vehicle.getVehicleId(),
                vehicle.getBrandName(),
                vehicle.getBrandModel(),
                vehicle.getVehicleType()
        );
    }

    public static BookingResponse bookingResponse (Booking booking) {
        return new BookingResponse(
                booking.getBookingId(),
                booking.getUser(),
                booking.getVehicle(),
                booking.getStartDate(),
                booking.getReturnDate(),
                booking.getDays()
        );
    }

    public static CustomerResponse customerResponse (Customer customer) {
        return new CustomerResponse(
                customer.getCustomerId(),
                customer.getUsername(),
                customer.getEmail(),
                customer.getAddress(),
                customer.getZipcode(),
                customer.getCountry(),
                customer.getBookings()
        );
    }
}

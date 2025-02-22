package com.rent.rentavehicle.service;

import com.rent.rentavehicle.entity.Booking;
import java.util.List;

public interface BookingService {
    Booking createBooking(Booking booking);
    Booking getBookingById(Long bookingId);
    List<Booking> getBookingsByCustomer(String customerId);
    List<Booking> getBookingsByVehicle(Long vehicleId);
    void deleteBooking(Long bookingId);
}

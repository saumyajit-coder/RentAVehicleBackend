package com.rent.rentavehicle.service;

import java.util.List;

import com.rent.rentavehicle.entity.Booking;

public interface BookingService {
    
    // Create a new booking
    Booking createBooking(Booking booking);

    // Get a booking by ID
    Booking getBookingById(Long bookingId);

    // Get all bookings for a customer (excluding vehicles under maintenance)
    List<Booking> getBookingsByCustomer(String customerId);

    // Get all bookings for a customer (admin access - includes maintenance vehicles)
    List<Booking> getAllBookingsByCustomer(String customerId);

    // Get all bookings for a specific vehicle
    List<Booking> getBookingsByVehicle(Long vehicleId);

    // Delete a booking by ID
    void deleteBooking(Long bookingId);
}
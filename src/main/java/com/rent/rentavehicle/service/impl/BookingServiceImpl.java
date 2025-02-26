package com.rent.rentavehicle.service.impl;

import com.rent.rentavehicle.entity.Booking;
import com.rent.rentavehicle.repositories.BookingRepository;
import com.rent.rentavehicle.service.BookingService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Booking createBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public Booking getBookingById(Long bookingId) {
        return bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found with ID: " + bookingId));
    }

    @Override
    public List<Booking> getBookingsByCustomer(String customerId) {
        return bookingRepository.findActiveBookingsByCustomer(customerId);
    }

    @Override
    public List<Booking> getAllBookingsByCustomer(String customerId) {
        return bookingRepository.findAllBookingsByCustomer(customerId);
    }

    @Override
    public List<Booking> getBookingsByVehicle(Long vehicleId) {
        return bookingRepository.findByVehicleId(vehicleId);
    }

    @Override
    public void deleteBooking(Long bookingId) {
        if (!bookingRepository.existsById(bookingId)) {
            throw new RuntimeException("Booking with ID " + bookingId + " does not exist.");
        }
        bookingRepository.deleteById(bookingId);
    }
}
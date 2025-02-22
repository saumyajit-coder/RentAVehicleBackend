package com.rent.rentavehicle.service.impl;

import com.rent.rentavehicle.entity.Booking;
import com.rent.rentavehicle.repositories.BookingRepository;
import com.rent.rentavehicle.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public Booking createBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public Booking getBookingById(Long bookingId) {
        Optional<Booking> booking = bookingRepository.findById(bookingId);
        return booking.orElse(null);
    }

    @Override
    public List<Booking> getBookingsByCustomer(String customerId) {
        return bookingRepository.findByCustomerCustomerId(customerId);
    }

    @Override
    public List<Booking> getBookingsByVehicle(Long vehicleId) {
        return bookingRepository.findByVehicleVehicleId(vehicleId);
    }

    @Override
    public void deleteBooking(Long bookingId) {
        bookingRepository.deleteById(bookingId);
    }
}

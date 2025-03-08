package com.rent.rentavehicle.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rent.rentavehicle.entity.Booking;
import com.rent.rentavehicle.service.BookingService;
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", allowCredentials = "true")
@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    // Create a new booking
    @PostMapping("/create")
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) {
        Booking savedBooking = bookingService.createBooking(booking);
        return ResponseEntity.ok(savedBooking);
    }

    // Get booking by ID
    @GetMapping("/{bookingId}")
    public ResponseEntity<Booking> getBookingById(@PathVariable Long bookingId) {
        Booking booking = bookingService.getBookingById(bookingId);
        return booking != null ? ResponseEntity.ok(booking) : ResponseEntity.notFound().build();
    }

 // Get all bookings for a customer (excluding maintenance vehicles)
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Booking>> getBookingsByCustomer(@PathVariable String customerId) {
        List<Booking> bookings = bookingService.getBookingsByCustomer(customerId);
        return ResponseEntity.ok(bookings);
    }

    // Get all bookings for a vehicle
    @GetMapping("/vehicle/{vehicleId}")
    public ResponseEntity<List<Booking>> getBookingsByVehicle(@PathVariable Long vehicleId) {
        List<Booking> bookings = bookingService.getBookingsByVehicle(vehicleId);
        return ResponseEntity.ok(bookings);
    }
    
 // Admin: Get all bookings (including maintenance vehicles)
    @GetMapping("/admin/bookings/{customerId}")
    public ResponseEntity<List<Booking>> getAllBookingsByCustomer(@PathVariable String customerId) {
        List<Booking> bookings = bookingService.getAllBookingsByCustomer(customerId);
        return ResponseEntity.ok(bookings);
    }


    // Delete a booking by ID
    @DeleteMapping("/{bookingId}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long bookingId) {
        bookingService.deleteBooking(bookingId);
        return ResponseEntity.noContent().build();
    }
}
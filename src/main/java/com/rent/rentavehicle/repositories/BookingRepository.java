/**
 * 
 */
package com.rent.rentavehicle.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rent.rentavehicle.entity.Booking;

/**
 * 
 */

@Repository
public interface BookingRepository extends CrudRepository<Booking, Long> {
    
    // Fetch all bookings for a customer, but exclude vehicles under maintenance
    @Query("SELECT b FROM Booking b WHERE b.customer.customerId = :customerId AND b.vehicle.status <> 'MAINTENANCE'")
    List<Booking> findActiveBookingsByCustomer(@Param("customerId") String customerId);

    // Fetch all bookings for an admin (including maintenance vehicles)
    @Query("SELECT b FROM Booking b WHERE b.customer.customerId = :customerId")
    List<Booking> findAllBookingsByCustomer(@Param("customerId") String customerId);
    
    @Query("SELECT b FROM Booking b WHERE b.vehicle.vehicleId = :vehicleId")
    List<Booking> findByVehicleId(@Param("vehicleId") Long vehicleId);
}
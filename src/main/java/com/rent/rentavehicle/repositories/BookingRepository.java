/**
 * 
 */
package com.rent.rentavehicle.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rent.rentavehicle.entity.Booking;

/**
 * 
 */
@Repository
public interface BookingRepository extends CrudRepository<Booking,Long> {
	List<Booking> findByCustomerCustomerId(String customerId);  // Get all bookings for a customer
    List<Booking> findByVehicleVehicleId(Long vehicleId);  // Get all bookings for a vehicle

}

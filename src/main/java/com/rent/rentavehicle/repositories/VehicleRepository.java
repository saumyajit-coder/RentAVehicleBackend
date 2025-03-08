package com.rent.rentavehicle.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rent.rentavehicle.entity.Vehicle;
import com.rent.rentavehicle.entity.VehicleDocument;

/**
 * Repository for Vehicle entity
 */
@Repository
public interface VehicleRepository extends CrudRepository<Vehicle, Long> {
    
    List<Vehicle> findByStatus(String status);
    List<Vehicle> findByCategory(String category); // Get all available vehicles
    Optional<Vehicle> findByVehicleNo(String vehicleNo);

    // ✅ New Query: Fetch Vehicle Image for a particular vehicle
    @Query("SELECT vd FROM VehicleDocument vd WHERE vd.vehicle.vehicleId = :vehicleId AND vd.documentType = 'VEHICLE_IMAGE'")
    Optional<VehicleDocument> findVehicleImageByVehicleId(@Param("vehicleId") Long vehicleId);
}

package com.rent.rentavehicle.service;

import java.util.List;
import java.util.Optional;

import com.rent.rentavehicle.entity.Vehicle;
import com.rent.rentavehicle.entity.VehicleDocument;

public interface VehicleService {
    Vehicle addVehicle(Vehicle vehicle);
    Vehicle getVehicleById(Long vehicleId);
    List<Vehicle> getAllVehicles();
    List<Vehicle> getAvailableVehicles();
    void deleteVehicle(Long vehicleId);

    public List<Vehicle> getVehiclesByCategory(String category);
    Optional<VehicleDocument> getVehicleImage(Long vehicleId);

   
}

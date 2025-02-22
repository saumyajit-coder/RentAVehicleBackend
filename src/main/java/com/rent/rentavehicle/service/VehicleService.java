package com.rent.rentavehicle.service;

import com.rent.rentavehicle.entity.Vehicle;
import java.util.List;

public interface VehicleService {
    Vehicle addVehicle(Vehicle vehicle);
    Vehicle getVehicleById(Long vehicleId);
    List<Vehicle> getAllVehicles();
    List<Vehicle> getAvailableVehicles();
    void deleteVehicle(Long vehicleId);
}

package com.rent.rentavehicle.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rent.rentavehicle.entity.Vehicle;
import com.rent.rentavehicle.entity.VehicleDocument;
import com.rent.rentavehicle.repositories.VehicleRepository;
import com.rent.rentavehicle.service.VehicleService;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override

    public Vehicle addVehicle(Vehicle vehicle) {
        // Check if vehicle with the same number already exists
        Optional<Vehicle> existingVehicle = vehicleRepository.findByVehicleNo(vehicle.getVehicleNo());
        if (existingVehicle.isPresent()) {
            throw new org.springframework.dao.DataIntegrityViolationException(
                    "Vehicle with this number already exists.");
        }
        return vehicleRepository.save(vehicle);
    }

    @Override
    public Vehicle getVehicleById(Long vehicleId) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(vehicleId);
        return vehicle.orElse(null);
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        return (List<Vehicle>) vehicleRepository.findAll();
    }

    @Override
    public List<Vehicle> getAvailableVehicles() {
        return (List<Vehicle>) vehicleRepository.findByStatus("available");
    }

    @Override
    public void deleteVehicle(Long vehicleId) {
        vehicleRepository.deleteById(vehicleId);
    }

    @Override
    public List<Vehicle> getVehiclesByCategory(String category) {
        return vehicleRepository.findByCategory(category);
    }

    @Override
    public Optional<VehicleDocument> getVehicleImage(Long vehicleId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getVehicleImage'");
    }
}

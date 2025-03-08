package com.rent.rentavehicle.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.rent.rentavehicle.entity.Vehicle;
import com.rent.rentavehicle.service.VehicleService;

@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", allowCredentials = "true")
@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    // // Add a new vehicle
    // @PostMapping("/create")
    // public ResponseEntity<Vehicle> addVehicle(@RequestBody Vehicle vehicle) {
    // Vehicle savedVehicle = vehicleService.addVehicle(vehicle);
    // return ResponseEntity.ok(savedVehicle);
    // }

    // Add a new vehicle

    // @PostMapping("/create")
    // public ResponseEntity<?> addVehicle(@RequestBody Vehicle vehicle) {
    //     try {
    //         System.out.println("Received Vehicle Data: " + vehicle); // Debugging Log

    //         if (vehicle.getVehicleNo() == null || vehicle.getModelName() == null) {
    //             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request: Missing required fields.");
    //         }

    //         Vehicle savedVehicle = vehicleService.addVehicle(vehicle);
    //         return ResponseEntity.ok(savedVehicle);
    //     } catch (org.springframework.dao.DataIntegrityViolationException e) {
    //         return ResponseEntity.status(HttpStatus.BAD_REQUEST)
    //                 .body("Duplicate entry: Vehicle with this number already exists!");
    //     } catch (Exception e) {
    //         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
    //                 .body("Error adding vehicle: " + e.getMessage());
    //     }
    // }


    @PostMapping("/create")
public ResponseEntity<Map<String, Object>> addVehicle(@RequestBody Vehicle vehicle) {
    try {
        System.out.println("Received Vehicle Data: " + vehicle); // Debugging Log

        if (vehicle.getVehicleNo() == null || vehicle.getModelName() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(createResponse("error", "Invalid request: Missing required fields.", null));
        }

        Vehicle savedVehicle = vehicleService.addVehicle(vehicle);

        return ResponseEntity.ok(createResponse("success", "Vehicle added successfully!", savedVehicle.getVehicleId()));

    } catch (org.springframework.dao.DataIntegrityViolationException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(createResponse("error", "Duplicate entry: Vehicle with this number already exists!", null));
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(createResponse("error", "Error adding vehicle: " + e.getMessage(), null));
    }
}
        private Map<String, Object> createResponse(String status, String message, Long vehicleId) {
                Map<String, Object> response = new HashMap<>();
                response.put("status", status);
                response.put("message", message);
                if (vehicleId != null) {
                    response.put("vehicleId",vehicleId);
        }
        
        return response;
    }


    // Get vehicle by ID
    @GetMapping("/{vehicleId}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable Long vehicleId) {
        Vehicle vehicle = vehicleService.getVehicleById(vehicleId);
        return (vehicle != null) ? ResponseEntity.ok(vehicle) : ResponseEntity.notFound().build();
    }

    // Get all vehicles
    @GetMapping("/all")
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        List<Vehicle> vehicles = vehicleService.getAllVehicles();
        return ResponseEntity.ok(vehicles);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Vehicle>> getVehiclesByCategory(@PathVariable String category) {
        List<Vehicle> vehicles = vehicleService.getVehiclesByCategory(category);
        return vehicles.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(vehicles);
    }

    // Get all available vehicles
    @GetMapping("/available")
    public ResponseEntity<List<Vehicle>> getAvailableVehicles() {
        List<Vehicle> availableVehicles = vehicleService.getAvailableVehicles();
        return ResponseEntity.ok(availableVehicles);
    }

    // Delete a vehicle by ID
    @DeleteMapping("/{vehicleId}")
    public ResponseEntity<String> deleteVehicle(@PathVariable Long vehicleId) {
        vehicleService.deleteVehicle(vehicleId);
        return ResponseEntity.ok("Vehicle deleted successfully.");
    }
}

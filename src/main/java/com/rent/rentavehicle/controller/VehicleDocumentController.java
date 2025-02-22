package com.rent.rentavehicle.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rent.rentavehicle.entity.VehicleDocument;
import com.rent.rentavehicle.service.VehicleDocumentService;

@RestController
@RequestMapping("/vehicle-documents")
public class VehicleDocumentController {

    private final VehicleDocumentService vehicleDocumentService;

    public VehicleDocumentController(VehicleDocumentService vehicleDocumentService) {
        this.vehicleDocumentService = vehicleDocumentService;
    }

    // Upload a new vehicle document
    @PostMapping
    public ResponseEntity<VehicleDocument> uploadDocument(@RequestBody VehicleDocument document) {
        VehicleDocument savedDocument = vehicleDocumentService.uploadDocument(document);
        return ResponseEntity.ok(savedDocument);
    }

    // Get all documents for a specific vehicle
    @GetMapping("/vehicle/{vehicleId}")
    public ResponseEntity<List<VehicleDocument>> getDocumentsByVehicle(@PathVariable Long vehicleId) {
        List<VehicleDocument> documents = vehicleDocumentService.getDocumentsByVehicle(vehicleId);
        return ResponseEntity.ok(documents);
    }

    // Delete a document by ID
    @DeleteMapping("/{documentId}")
    public ResponseEntity<String> deleteDocument(@PathVariable Long documentId) {
        vehicleDocumentService.deleteDocument(documentId);
        return ResponseEntity.ok("Document deleted successfully.");
    }
}

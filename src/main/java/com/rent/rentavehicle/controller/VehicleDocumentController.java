package com.rent.rentavehicle.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rent.rentavehicle.entity.VehicleDocument;
import com.rent.rentavehicle.service.S3StorageService;
import com.rent.rentavehicle.service.VehicleDocumentService;

@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", allowCredentials = "true")
@RestController
@RequestMapping("/admin/vehicle-documents")
public class VehicleDocumentController {

    private final VehicleDocumentService vehicleDocumentService;
    private final S3StorageService s3StorageService;

    public VehicleDocumentController(VehicleDocumentService vehicleDocumentService, S3StorageService s3StorageService) {
        this.vehicleDocumentService = vehicleDocumentService;
        this.s3StorageService = s3StorageService;
    }

    
    @PostMapping("/upload")
    public ResponseEntity<?> uploadVehicleDocument(
            @RequestParam("file") MultipartFile file,
            @RequestParam("vehicleId") Long vehicleId,
            @RequestParam("documentType") String documentType) { // <-- Change to String

        // 🔹 DEBUGGING LOGS
        System.out.println("Received file: " + (file != null ? file.getOriginalFilename() : "No file received"));
        System.out.println("Received vehicleId: " + vehicleId);
        System.out.println("Received documentType: " + documentType);

        if (file == null || file.isEmpty()) {
            return ResponseEntity.badRequest().body("No file uploaded.");
        }

        if (vehicleId == null) {
            return ResponseEntity.badRequest().body("Vehicle ID is missing.");
        }

        try {
            // Upload file to S3
            String fileUrl = s3StorageService.uploadFile(file);

            // Convert documentType string to Enum (if needed)
            VehicleDocument.DocumentType docTypeEnum;
            try {
                docTypeEnum = VehicleDocument.DocumentType.valueOf(documentType.toUpperCase().replace(" ", "_"));
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().body("Invalid documentType: " + documentType);
            }

            // Save document to DB
            VehicleDocument savedDocument = vehicleDocumentService.uploadDocument(vehicleId, docTypeEnum, fileUrl);
            return ResponseEntity.ok(savedDocument);
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("File upload failed: " + e.getMessage());
        }
    }

    // Get all documents for a specific vehicle
    @GetMapping("/vehicle/{vehicleId}")
    public ResponseEntity<List<VehicleDocument>> getDocumentsByVehicle(@PathVariable Long vehicleId) {
        List<VehicleDocument> documents = vehicleDocumentService.getDocumentsByVehicle(vehicleId);
        return ResponseEntity.ok(documents);
    }

    // ✅ New API: Fetch vehicle image by vehicleId
    @GetMapping("/{vehicleId}/Vehicle_Image")
    public ResponseEntity<Map<String, Object>> getVehicleImageByVehicleId(@PathVariable Long vehicleId) {
        Optional<VehicleDocument> vehicleImageDoc = vehicleDocumentService.getVehicleImageByVehicleId(vehicleId);

        if (vehicleImageDoc.isPresent()) {
            return ResponseEntity.ok(Map.of(
                "status", "success",
                "message", "Vehicle image fetched successfully.",
                "vehicleImage", vehicleImageDoc.get().getFilePath()
            ));
        } else {
            return ResponseEntity.ok(Map.of(
                "status", "success",
                "message", "No vehicle image found.",
                "vehicleImage", null
            ));
        }
    }

    
    

    // Delete a document by ID
    @DeleteMapping("/{documentId}")
    public ResponseEntity<String> deleteDocument(@PathVariable Long documentId) {
        vehicleDocumentService.deleteDocument(documentId);
        return ResponseEntity.ok("Document deleted successfully.");
    }
}
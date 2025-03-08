package com.rent.rentavehicle.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rent.rentavehicle.entity.Vehicle;
import com.rent.rentavehicle.entity.VehicleDocument;
import com.rent.rentavehicle.repositories.VehicleDocumentRepository;
import com.rent.rentavehicle.service.VehicleDocumentService;
import com.rent.rentavehicle.service.VehicleService;

@Service
public class VehicleDocumentServiceImpl implements VehicleDocumentService {

    @Autowired
    private VehicleDocumentRepository vehicleDocumentRepository;
    
    private final VehicleService vehicleService;

    public VehicleDocumentServiceImpl(VehicleDocumentRepository vehicleDocumentRepository, VehicleService vehicleService) {
        this.vehicleDocumentRepository = vehicleDocumentRepository;
        this.vehicleService = vehicleService;
    }

    @Override
    public VehicleDocument uploadDocument(Long vehicleId, VehicleDocument.DocumentType documentType, String fileUrl) {
        // ✅ Ensure the vehicle exists before saving the document
        Optional<Vehicle> vehicleOpt = Optional.ofNullable(vehicleService.getVehicleById(vehicleId));

        if (vehicleOpt.isEmpty()) {
            throw new IllegalArgumentException("Vehicle with ID " + vehicleId + " not found.");
        }

        Vehicle vehicle = vehicleOpt.get();

        VehicleDocument document = new VehicleDocument();
        document.setVehicle(vehicle);
        document.setDocumentType(documentType);
        document.setFilePath(fileUrl); // ✅ Store AWS S3 URL
        document.setUploadedAt(LocalDateTime.now());

        return vehicleDocumentRepository.save(document);
    }

    @Override
    public List<VehicleDocument> getDocumentsByVehicle(Long vehicleId) {
        return vehicleDocumentRepository.findByVehicleVehicleId(vehicleId);
    }

    @Override
    public void deleteDocument(Long documentId) {
        vehicleDocumentRepository.deleteById(documentId);
    }

    // ✅ New method to fetch only the vehicle image
    @Override
    public Optional<VehicleDocument> getVehicleImageByVehicleId(Long vehicleId) {
        return vehicleDocumentRepository.findByVehicleVehicleId(vehicleId)
                .stream()
                .filter(doc -> doc.getDocumentType() == VehicleDocument.DocumentType.VEHICLE_IMAGE)
                .findFirst();
    }
}

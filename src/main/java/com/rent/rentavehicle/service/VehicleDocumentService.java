package com.rent.rentavehicle.service;

import java.util.List;
import java.util.Optional;

import com.rent.rentavehicle.entity.VehicleDocument;

public interface VehicleDocumentService {
    VehicleDocument uploadDocument(Long vehicleId, VehicleDocument.DocumentType documentType, String fileUrl);
    List<VehicleDocument> getDocumentsByVehicle(Long vehicleId);
    void deleteDocument(Long documentId);

    // ✅ New method to fetch the vehicle image
    Optional<VehicleDocument> getVehicleImageByVehicleId(Long vehicleId);
}

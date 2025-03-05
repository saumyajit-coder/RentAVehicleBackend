package com.rent.rentavehicle.service;

import java.util.List;

import com.rent.rentavehicle.entity.VehicleDocument;

public interface VehicleDocumentService {
    VehicleDocument uploadDocument(Long vehicleId, VehicleDocument.DocumentType documentType, String fileUrl);
    List<VehicleDocument> getDocumentsByVehicle(Long vehicleId);
    void deleteDocument(Long documentId);
}
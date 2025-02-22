package com.rent.rentavehicle.service;

import com.rent.rentavehicle.entity.VehicleDocument;
import java.util.List;

public interface VehicleDocumentService {
    VehicleDocument uploadDocument(VehicleDocument document);
    List<VehicleDocument> getDocumentsByVehicle(Long vehicleId);
    void deleteDocument(Long documentId);
}

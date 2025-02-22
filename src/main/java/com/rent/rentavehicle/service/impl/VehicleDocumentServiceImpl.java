package com.rent.rentavehicle.service.impl;

import com.rent.rentavehicle.entity.VehicleDocument;
import com.rent.rentavehicle.repositories.VehicleDocumentRepository;
import com.rent.rentavehicle.service.VehicleDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleDocumentServiceImpl implements VehicleDocumentService {

    @Autowired
    private VehicleDocumentRepository vehicleDocumentRepository;

    @Override
    public VehicleDocument uploadDocument(VehicleDocument document) {
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
}
